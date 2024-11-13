package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.MenuMapper;
import com.example.mango_admin.model.Menu;
import com.example.mango_admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public int save(Menu menu) {
        if(menu.getId() == null || menu.getId() == 0) {
            return menuMapper.insertSelective(menu);
        }
        if(menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        return menuMapper.updateByPrimaryKeySelective(menu);
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
    public List<Menu> findAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Menu> findTree(String userName, int menuType) {
        return Collections.emptyList();
    }

    @Override
    public List<Menu> findByUser(String userName) {
        return Collections.emptyList();
    }

    private void findChildren(List<Menu> sysMenus, List<Menu> menus, int menuType) {
        for (Menu sysMenu : sysMenus) {

        }
    }

    private boolean exists(List<Menu> menus, Menu menu) {
        boolean exists = false;
        for (Menu menu1 : menus) {
            if (menu1.getId().equals(menu.getId())) {
                exists = true;
            }
        }
        return exists;
    }
}
