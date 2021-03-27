package com.danao.sr.service;

import com.danao.sr.vo.DangaoUserVO;

public interface DangaoService {

    DangaoUserVO query(String id);

    void insert(DangaoUserVO dangaoUserVO);

}
