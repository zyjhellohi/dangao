package com.danao.sr.service.impl;

import com.danao.sr.mapper.SubMapper;
import com.danao.sr.service.SubService;
import com.danao.sr.vo.DangaoSubVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;


@Service
public class DGSubServiceImpl implements SubService {

    @Autowired
    SubMapper subMapper;


    @Override
    @Transient
    public DangaoSubVO query(DangaoSubVO dangaoSubVO) {
        return subMapper.query(dangaoSubVO);
    }

    @Override
    public void insert(DangaoSubVO dangaoSubVO) {
        subMapper.insert(dangaoSubVO);
    }

    @Override
    @Transient
    public void update(DangaoSubVO dangaoSubVO) {
        subMapper.update(dangaoSubVO);
    }
}
