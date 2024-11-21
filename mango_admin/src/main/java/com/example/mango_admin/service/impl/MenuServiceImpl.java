package com.example.mango_admin.service.impl;

import com.example.mango_admin.constant.SysConstants;
import com.example.mango_admin.mapper.MenuMapper;
import com.example.mango_admin.model.Menu;
import com.example.mango_admin.service.MenuService;
import org.example.common.page.MyBatisPageHelper;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public int save(Menu menu) {
        if (menu.getId() == null || menu.getId() == 0) {
            return menuMapper.insert(menu);
        }
        return menuMapper.updateByPrimaryKey(menu);
    }

    @Override
    public int delete(Menu menu) {
        return menuMapper.deleteByPrimaryKey(menu.getId());
    }

    @Override
    public int delete(List<Menu> list) {
        for (Menu menu : list) {
            delete(menu);
        }
        return list.size();
    }

    @Override
    public Menu findById(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MyBatisPageHelper.findPage(pageRequest, menuMapper);
    }

    @Override
    public List<Menu> findByUser(String userName) {
        if (userName == null || userName.isEmpty() || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
            return menuMapper.selectAll();
        }
        return menuMapper.findByUserName(userName);
    }

    @Override
    public List<Menu> findTree(String userName, int menuType) {
        List<Menu> resultMenus = new ArrayList<>();
        List<Menu> userMenus = findByUser(userName);
        for (Menu menu : userMenus) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                if (!exists(resultMenus, menu)) {
                    resultMenus.add(menu);
                }
            }
        }
        resultMenus.sort(Comparator.comparing(Menu::getOrderNum));
        findChildren(resultMenus, userMenus, menuType);
        return resultMenus;
    }

    private void findChildren(List<Menu> resultMenus, List<Menu> userMenus, int menuType) {
        for (Menu parentMenu : resultMenus) {
            List<Menu> childMenus = new ArrayList<>();
            for (Menu menu : userMenus) {
                if (menuType == 1 && menu.getType() == 2) {
                    continue;
                }
                if (parentMenu.getId() != null && menu.getParentId().equals(parentMenu.getId())) {
                    menu.setParentName(parentMenu.getName());
                    menu.setLevel(parentMenu.getLevel() + 1);
                    if (!exists(childMenus, menu)) {
                        childMenus.add(menu);
                    }
                }
            }
            parentMenu.setChildren(childMenus);
            childMenus.sort(Comparator.comparing(Menu::getOrderNum));
            findChildren(childMenus, userMenus, menuType);
        }
    }

    private boolean exists(List<Menu> menus, Menu menu) {
        boolean exist = false;
        for (Menu m : menus) {
            if (m.getId().equals(menu.getId())) {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
