package com.danao.sr.mapper;

import com.danao.sr.vo.DangaoSubLogVO;

public interface SubLogMapper {

    void insert(DangaoSubLogVO dangaoSubLogVO);

    DangaoSubLogVO query(DangaoSubLogVO dangaoSubLogVO);

    void update(DangaoSubLogVO dangaoSubLogVO);
}
