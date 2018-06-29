package com.library.controller;

import com.library.tool.MobileIf;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LinTi on 2016/10/10.
 */
public class BaseController {

    public String getm(HttpServletRequest request) {
        if (new MobileIf().checkAgentIsMobile(request.getHeader("User-Agent"))) {
            return ".m";
        } else {
            return "";
        }
    }

}
