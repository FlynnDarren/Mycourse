package com.school.service.impl;

import com.school.entiey.Clazz;
import com.school.entiey.Page;
import com.school.repository.impl.ClazzRepositoryImpl;
import com.school.service.ClazzService;

import java.util.List;

public class ClazzServiceImpl implements ClazzService {
    ClazzRepositoryImpl clazzRepositoryImpl = new ClazzRepositoryImpl();
    @Override
    public boolean editClazz(Clazz clazz) {
       boolean edit = clazzRepositoryImpl.editClazz(clazz);
        clazzRepositoryImpl.closeCon();
        return edit;
    }

    @Override
    public boolean deleteClazz(int id) {
      boolean deClazz =   clazzRepositoryImpl.deleteClazz(id);
        clazzRepositoryImpl.closeCon();

        return deClazz;
    }

    @Override
    public boolean addClazz(Clazz clazz) {
        boolean adclz = clazzRepositoryImpl.addClazz(clazz);
        clazzRepositoryImpl.closeCon();
        return adclz;
    }

    @Override
    public List<Clazz> getClazzList(Clazz clazz, Page page) {
        List<Clazz> list= clazzRepositoryImpl.getClazzList(clazz,page);
        clazzRepositoryImpl.closeCon();
        return list;
    }

    @Override
    public int getClazzListTotal(Clazz clazz) {
        int tota = clazzRepositoryImpl.getClazzListTotal(clazz);
        clazzRepositoryImpl.closeCon();
        return tota;
    }
}
