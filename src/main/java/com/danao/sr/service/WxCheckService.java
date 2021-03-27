package com.danao.sr.service;

import com.danao.sr.vo.DangaoSubVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface WxCheckService {
    void checkJurisdiction(HttpServletRequest req,HttpServletResponse resp);
    void processingMessages(HttpServletRequest req,HttpServletResponse resp) throws Exception;
}
