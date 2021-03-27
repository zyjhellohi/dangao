package com.danao.sr.service.impl;

import com.danao.sr.mapper.SubLogMapper;
import com.danao.sr.service.SubLogService;
import com.danao.sr.vo.DangaoSubLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DGSubLogServiceImpl implements SubLogService {

    @Autowired
    SubLogMapper subLogMapper;

    @Override
    public DangaoSubLogVO query(DangaoSubLogVO dangaoSubLogVO) {
        return subLogMapper.query(dangaoSubLogVO);
    }

    @Override
    public void insert(DangaoSubLogVO dangaoSubLogVO) {
        subLogMapper.insert(dangaoSubLogVO);
    }
}
