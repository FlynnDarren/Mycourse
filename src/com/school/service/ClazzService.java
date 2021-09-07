package com.school.service;

import com.school.entiey.Clazz;
import com.school.entiey.Page;

import java.util.List;

public interface ClazzService {
    public boolean editClazz(Clazz clazz);
    public boolean deleteClazz(int id);
    public boolean addClazz(Clazz clazz);
    public List<Clazz> getClazzList(Clazz clazz, Page page);
    public int getClazzListTotal(Clazz clazz);
}
