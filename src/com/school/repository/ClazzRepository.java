package com.school.repository;

import com.school.entiey.Clazz;
import com.school.entiey.Page;

import java.util.List;

public interface ClazzRepository {
    public List<Clazz> getClazzList(Clazz clazz, Page page);
    public int getClazzListTotal(Clazz clazz);
    public boolean addClazz(Clazz clazz);
    public boolean deleteClazz(int id);
    public boolean editClazz(Clazz clazz);
}
