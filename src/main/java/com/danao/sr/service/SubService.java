package com.danao.sr.service;

import com.danao.sr.vo.DangaoSubVO;

public interface SubService {

    DangaoSubVO query(DangaoSubVO dangaoSubVO);

    void insert(DangaoSubVO dangaoSubVO);

    void update(DangaoSubVO dangaoSubVO);
}
