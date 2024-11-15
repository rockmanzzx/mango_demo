package com.example.mango_admin.service.impl;

import com.example.mango_admin.mapper.DeptMapper;
import com.example.mango_admin.model.Dept;
import com.example.mango_admin.service.DeptService;
import org.example.common.page.MyBatisPageHelper;
import org.example.common.page.PageRequest;
import org.example.common.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findTree() {
        List<Dept> parentDepts = new ArrayList<>();
        List<Dept> allDepts = deptMapper.selectAll();
        for (Dept dept : allDepts) {
            if (dept.getParentId() == null || dept.getParentId() == 0) {
                dept.setLevel(0);
                parentDepts.add(dept);
            }
        }
        findChildren(parentDepts, allDepts);
        return parentDepts;
    }

    private void findChildren(List<Dept> parentDepts, List<Dept> allDepts) {
        for (Dept parentDept : parentDepts) {
            List<Dept> childDepts = new ArrayList<>();
            for (Dept dept : allDepts) {
                if (parentDept.getId() != null && parentDept.getId().equals(dept.getParentId())) {
                    dept.setParentName(parentDept.getName());
                    dept.setLevel(parentDept.getLevel() + 1);
                    childDepts.add(dept);
                }
            }
            parentDept.setChildren(childDepts);
            findChildren(childDepts, allDepts);
        }
    }

    @Override
    public int save(Dept dept) {
        if (dept.getId() == null || dept.getId() == 0) {
            return deptMapper.insert(dept);
        }
        return deptMapper.updateByPrimaryKey(dept);
    }

    @Override
    public int delete(Dept dept) {
        return deptMapper.deleteByPrimaryKey(dept.getId());
    }

    @Override
    public int delete(List<Dept> list) {
        for (Dept dept : list) {
            delete(dept);
        }
        return list.size();
    }

    @Override
    public Dept findById(Long id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MyBatisPageHelper.findPage(pageRequest, deptMapper);
    }
}
