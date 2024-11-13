package com.example.mango_admin.service;

import com.example.mango_admin.model.Menu;
import org.example.common.service.CrudService;

import java.util.List;

public interface MenuService extends CrudService<Menu> {

    /**
     *
     * @param userName
     * @param menuType 获取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
     * @return
     */
    List<Menu> findTree(String userName, int menuType);

    List<Menu> findByUser(String userName);

}
