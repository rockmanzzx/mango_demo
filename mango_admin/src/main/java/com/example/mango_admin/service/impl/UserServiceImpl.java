package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.UserMapper;
import com.example.mango_admin.model.User;
import com.example.mango_admin.service.UserService;
import com.example.mango_admin.util.RequestValidator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.core.page.MyBatisPageHelper;
import org.example.core.page.PageRequest;
import org.example.core.page.PageResult;
import org.example.core.util.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        if (user.getId() == null || user.getId() == 0) {
            return userMapper.insert(user);
        }
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
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
        return 1;
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MyBatisPageHelper.findPage(pageRequest, userMapper);
    }

    @Override
    public PageResult findPageByName(PageRequest pageRequest) {
        RequestValidator.validatePageRequest(pageRequest, "name");
        String name = (String) pageRequest.getParams().get("name");
        return MyBatisPageHelper.findPage(pageRequest, userMapper, "selectByName", name);
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

