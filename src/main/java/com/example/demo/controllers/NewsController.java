package com.example.demo.controllers;

import com.example.demo.models.News;
import com.example.demo.models.User;
import com.example.demo.models.UserDetail;
import com.example.demo.services.NewsService;
import com.example.demo.services.UserDetailService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新闻列表、详情控制器
 * 返回thymeleaf页面
 * @author ggg1235
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserDetailService userDetailService;

    private static Map<Integer, String> map;
    static {
        map = new HashMap<>();
        map.put(1,"国内新闻");
        map.put(2,"国际新闻");
        map.put(3,"本地新闻");
        map.put(4,"娱乐新闻");
        map.put(5,"政要新闻");
    }

    private static String []sorts={"DESC","ASC"};

    @RequestMapping("/list/{id}")
    public ModelAndView getNewsList(@PathVariable("id") Integer id, Model model,
                                    @RequestParam(value = "sort",defaultValue = "0") Integer sort, //奇数为ASC,偶数为DESC
                                    @RequestParam(value = "sortableFields",defaultValue = "auditTime") String sortableFields,
                                    @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                    @RequestParam(value = "pageSize",defaultValue = "15") Integer pageSize){
        model.addAttribute("title",map.get(id));
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByLoginName(name);

        model.addAttribute("isLogin",user != null);

        if (user!=null){
            model.addAttribute("user",user);
        }


        String sortType=sorts[sort%2];
        Sort sort1 = "ASC".equals(sortType) ? new Sort(Sort.Direction.ASC, sortableFields) : new Sort(Sort.Direction.DESC, sortableFields);
        Pageable pageable=new PageRequest(pageIndex-1,pageSize,sort1);

        Page<News> page=newsService.getNewsByTypeAndStatus(id,1,pageable);
        List<News> list = page.getContent();

        model.addAttribute("newsList",list);
        model.addAttribute("page",page);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("id",id);
        return new ModelAndView("news-list","newsModel",model);
    }

    @RequestMapping("/detail/{id}")
    public ModelAndView getNewsDetail(@PathVariable("id") Long id,
                                      Model model){

        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByLoginName(name);

        if (user!=null){
            model.addAttribute("user",user);
        }

        model.addAttribute("isLogin",user != null);
        News news=newsService.getNewsById(id);

        List<News> list=newsService.getNewsByTypeAndStatus(news.getnewsType(),1);
        Integer index=list.indexOf(news);

        Integer previous,next;
        if (index==0){
            previous=0;
        }else {
            previous=index-1;
        }

        if (index==list.size()-1){
            next=index;
        }else{
            next=index+1;
        }

        model.addAttribute("previous",list.get(previous).getnewsId());
        model.addAttribute("next",list.get(next).getnewsId());

        model.addAttribute("news",news);
        model.addAttribute("type",map.get(news.getnewsType()));
        return new ModelAndView("news-detail","newsModel",model);
    }

}
