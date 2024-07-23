package com.example.mango_admin.mapper;

import com.example.mango_admin.model.RoleMenu;
import java.util.List;

public interface RoleMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated
     */
    int insert(RoleMenu row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated
     */
    RoleMenu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated
     */
    List<RoleMenu> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RoleMenu row);
}