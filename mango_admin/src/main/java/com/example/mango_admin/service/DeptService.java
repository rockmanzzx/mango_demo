package com.example.mango_admin.service;

import com.example.mango_admin.model.Dept;
import org.example.common.service.CrudService;

import java.util.List;

public interface DeptService extends CrudService<Dept> {

    List<Dept> findTree();
}
