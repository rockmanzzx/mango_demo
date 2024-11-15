package com.example.mango_admin.mapper;

import com.example.mango_admin.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated
     */
    int insert(Menu row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated
     */
    Menu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated
     */
    List<Menu> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Menu row);

    int updateByPrimaryKeySelective(Menu row);

    int insertSelective(Menu record);

    List<Menu> findPage();

    List<Menu> findPageByName(@Param("name") String name);

    List<Menu> findAll();

    List<Menu> findByUserName(@Param("userName") String userName);

    List<Menu> findRoleMenus(@Param("roleId") Long roleId);

}