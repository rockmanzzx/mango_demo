package com.example.mango_admin.mapper;

import com.example.mango_admin.model.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    int insert(Dict row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    Dict selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    List<Dict> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Dict row);

    List<Dict> findPage();
    List<Dict> findPageByLabel(@Param(value = "label") String label);
    List<Dict> findByLabel(@Param(value = "label") String label);
}