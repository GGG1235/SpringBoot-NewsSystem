package com.example.demo.controllers;

import com.example.demo.models.File;
import com.example.demo.models.User;
import com.example.demo.services.FileService;
import com.example.demo.services.UserService;
import com.example.ueditor.configs.ActionEnter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件上传统一请求路径
 * Created by ldb on 2019/1/5.
 * @author ggg1235
 */

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class UeditorController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    private static Map<String, Integer> fileTypes;
    static {
        fileTypes = new HashMap<>();
        fileTypes.put(".jpg",1);
        fileTypes.put(".JPG",1);
        fileTypes.put(".jpeg",1);
        fileTypes.put(".JPEG",1);
        fileTypes.put(".gif",3);
        fileTypes.put(".GIF",3);
        fileTypes.put(".mp4",2);
        fileTypes.put(".MP4",2);
        fileTypes.put(".png",1);
        fileTypes.put(".PNG",1);
        fileTypes.put(".avi",2);
        fileTypes.put(".AVI",2);
    }

    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();


            JSONObject json=new JSONObject(exec);     //json字符串转map
            Map<String,Object> map=new ConcurrentHashMap<>();
            Iterator it = json.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object value = json.get(key);
                map.put(key, value);
            }


            writer.write(exec);
            writer.flush();
            writer.close();
            if (map.get("url")!=null) {
                String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userService.getUserByLoginName(UserName);
                Long userId=user.getuserId();
                BigDecimal size=new BigDecimal(map.get("size").toString());
                String fileName=map.get("title").toString();
                String filePath=map.get("url").toString();
                String fileOriginalName=map.get("original").toString();
                Integer fileType;
                try {
                    fileType = fileTypes.get(map.get("type").toString());
                }catch (Exception e){
                    fileType=4;
                }
                fileService.saveOrUpdateFile(new File(fileName,fileOriginalName,size,filePath,userId,fileType,new Date()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
