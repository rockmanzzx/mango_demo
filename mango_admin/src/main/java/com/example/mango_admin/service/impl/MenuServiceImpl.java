package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.MenuMapper;
import com.example.mango_admin.model.Menu;
import com.example.mango_admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        return update(menu);
    }

    @Override
    public int update(Menu menu) {
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
    public List<Menu> findAll() {
        return menuMapper.selectAll();
    }
}
