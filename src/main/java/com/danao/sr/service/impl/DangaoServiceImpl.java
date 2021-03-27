package com.danao.sr.service.impl;

import com.danao.sr.mapper.UserMapper;
import com.danao.sr.service.DangaoService;
import com.danao.sr.vo.DangaoUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DangaoServiceImpl implements DangaoService {

    @Autowired
    UserMapper dangaoMapperDao;

    @Override
    public DangaoUserVO query(String id) {
        return dangaoMapperDao.queryshs();
    }

    @Override
    public void insert(DangaoUserVO dangaoUserVO) {
        dangaoMapperDao.insert(dangaoUserVO);
    }


}
