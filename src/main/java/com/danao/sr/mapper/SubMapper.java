package com.danao.sr.mapper;

import com.danao.sr.vo.DangaoSubVO;


public interface SubMapper {

    void insert(DangaoSubVO dangaoSubVO);

    DangaoSubVO query(DangaoSubVO dangaoSubVO);

    void update(DangaoSubVO dangaoSubVO);
}
