package org.example.common.service;


import java.util.List;

public interface CrudService<T> {

    int save(T t);
    int update(T t);
    int delete(T t);
    int delete(List<T> list);
    T findById(Long id);
    List<T> findAll();

}
