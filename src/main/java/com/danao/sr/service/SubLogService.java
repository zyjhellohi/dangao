package com.danao.sr.service;

import com.danao.sr.vo.DangaoSubLogVO;

public interface SubLogService {

    DangaoSubLogVO query(DangaoSubLogVO dangaoSubLogVO);

    void insert(DangaoSubLogVO dangaoSubLogVO);
}
