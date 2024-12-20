package com.example.mango_admin.mapper;

import com.example.mango_admin.model.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    int insert(LoginLog row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    LoginLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    List<LoginLog> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LoginLog row);

    List<LoginLog> findPage();

    List<LoginLog> findPageByUserName(@Param("userName") String userName);

    List<LoginLog> findPageByStatus(@Param("status") String status);

    List<LoginLog> findPageByUserNameAndStatus(@Param("userName") String userName, @Param("status") String status);

    List<LoginLog> findByUserNameAndStatus(@Param("userName") String userName, @Param("status") String status);
}