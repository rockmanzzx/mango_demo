package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.RoleMapper;
import com.example.mango_admin.mapper.UserMapper;
import com.example.mango_admin.mapper.UserRoleMapper;
import com.example.mango_admin.model.Menu;
import com.example.mango_admin.model.Role;
import com.example.mango_admin.model.User;
import com.example.mango_admin.model.UserRole;
import com.example.mango_admin.service.MenuService;
import com.example.mango_admin.service.UserService;
import com.example.mango_admin.util.RequestValidator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.common.exception.GlobalExceptionHandler;
import org.example.common.page.MyBatisPageHelper;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.example.common.util.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public int save(User user) {
        Long id = null;
        if (user.getId() == null || user.getId() == 0) {
            userMapper.insertSelective(user);
            id = user.getId();
        } else {
            userMapper.updateByPrimaryKey(user);
        }
        if (id != null && id == 0) {
            return 1;
        }
        if (id != null) {
            for (UserRole userRole : user.getUserRoles()) {
                userRole.setUserId(id);
            }
        } else {
            if (!user.getUserRoles().isEmpty()) {
                userRoleMapper.deleteByUserId(user.getId());
            }
        }
        for (UserRole userRole : user.getUserRoles()) {
            userRoleMapper.insert(userRole);
        }
        return 1;
    }

    @Override
    public int delete(User user) {
        return userMapper.deleteByPrimaryKey(user.getId());
    }

    @Override
    public int delete(List<User> list) {
        for (User user : list) {
            delete(user);
        }
        return list.size();
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object name = pageRequest.getParamValue("name");
        Object email = pageRequest.getParamValue("email");
        if (name != null) {
            if (email != null) {
                pageResult = MyBatisPageHelper.findPage(pageRequest, userMapper, "findPageByNameAndEmail", name, email);
            } else {
                pageResult = MyBatisPageHelper.findPage(pageRequest, userMapper, "findPageByName", name);
            }
        } else {
            pageResult = MyBatisPageHelper.findPage(pageRequest, userMapper);
        }
        return pageResult;
    }

    @Override
    public User findByName(String username) {
        User user = userMapper.findByName(username);
        List<UserRole> userRoles = userRoleMapper.findUserRoles(user.getId());
        user.setUserRoles(userRoles);
        user.setRoleNames(getRoleNames(userRoles));
        return user;
    }

    private void findUserRoles(PageResult pageResult) {
        List<?> content = pageResult.getContent();
        for (Object object : content) {
            User user = (User) object;
            List<UserRole> userRoles = userRoleMapper.findUserRoles(user.getId());
            user.setUserRoles(userRoles);
            user.setRoleNames(getRoleNames(userRoles));
        }
    }

    private String getRoleNames(List<UserRole> userRoles) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Iterator<UserRole> iterator = userRoles.iterator(); iterator.hasNext();) {
            UserRole userRole = iterator.next();
            Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
            if (role == null) {
                continue;
            }
            stringBuilder.append(role.getRemark());
            if (iterator.hasNext()) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public Set<String> findPermissions(String name) {
        Set<String> perms = new HashSet<>();
        List<Menu> menus = menuService.findByUser(name);
        for (Menu menu : menus) {
            if (menu.getPerms() != null && !menu.getPerms().isEmpty()) {
                perms.add(menu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public List<UserRole> findUserRoles(Long userId) {
        return userRoleMapper.findUserRoles(userId);
    }

    @Override
    public File createUserExcleFile(PageRequest pageRequest) {
        PageResult pageResult = findPage(pageRequest);
        return createUserExcelFile(pageResult.getContent());
    }

    public static File createUserExcelFile(List<User> records) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Create header row
        String[] headers = {
                "编号", "用户名", "昵称", "头像", "密码", "加密盐", "邮箱", "手机号", "状态", "机构ID", "创建人", "创建时间", "更新人", "更新时间", "是否删除"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Fill data rows
        int rowNum = 1;
        for (User user : records) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getNickName());
            row.createCell(3).setCellValue(user.getAvatar());
            row.createCell(4).setCellValue(user.getPassword());
            row.createCell(5).setCellValue(user.getSalt());
            row.createCell(6).setCellValue(user.getEmail());
            row.createCell(7).setCellValue(user.getMobile());
            row.createCell(8).setCellValue(user.getStatus());
            row.createCell(9).setCellValue(user.getDeptId());
            row.createCell(10).setCellValue(user.getCreateBy());
            row.createCell(11).setCellValue(user.getCreateTime().toString());
            row.createCell(12).setCellValue(user.getLastUpdateBy());
            row.createCell(13).setCellValue(user.getLastUpdateTime().toString());
            row.createCell(14).setCellValue(user.getDelFlag());
        }

        return PoiUtils.createExcelFile(workbook, "download_user");
    }
}

