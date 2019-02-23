package com.example.demo.controllers.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 异常页控制器
 * @author ggg1235
 */
@Controller
public class ErrorPageController implements ErrorController {


    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String error(HttpServletResponse response){
        int code=response.getStatus();
        String path="/error/404";
        switch (code){
            case 403:
                path="/error/403";
                break;
            case 404:
                path="/error/404";
                break;
            case 500:
                path="/error/500";
                break;

        }
        return path;
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
