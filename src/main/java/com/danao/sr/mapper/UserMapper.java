package com.danao.sr.mapper;

import com.danao.sr.vo.DangaoUserVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

//    void inser(DangaoUserVo dangaoUserVo);
//
    DangaoUserVO queryshs();
//
//    void delete(String id);
}
