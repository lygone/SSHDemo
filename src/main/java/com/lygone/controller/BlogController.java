package com.lygone.controller;

import com.lygone.model.BlogEntity;
import com.lygone.model.UserEntity;
import com.lygone.repository.BlogRepository;
import com.lygone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
@Controller
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    UserRepository userRepository;
    @RequestMapping(value = "/admin/blogs",method = RequestMethod.GET)
    public String showBlog(ModelMap modelMap){
        List<BlogEntity> blogList=blogRepository.findAll();
        modelMap.addAttribute("blogList",blogList);
        return "admin/blogs";
    }
    @RequestMapping(value = "/admin/blogs/add", method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap) {
        List<UserEntity> userList = userRepository.findAll();
// 向jsp注入用户列表
        modelMap.addAttribute("userList", userList);
        return "admin/addblog";
    }
    @RequestMapping(value = "/admin/blogs/addP",method = RequestMethod.POST)
    public String addBlogPost(@ModelAttribute("blog")BlogEntity blogEntity){
        System.out.println(blogEntity.getTitle());
// 打印博客作者
        System.out.println(blogEntity.getUserByUserId().getNickname());
        blogRepository.saveAndFlush(blogEntity);
        return "redirect:/admin/blogs";
    }
    @RequestMapping(value = "/admin/blogs/show/{id}",method = RequestMethod.GET)
    public String showBlog(@PathVariable("id")int id,ModelMap modelMap){
        BlogEntity blogEntity=blogRepository.findOne(id);
        modelMap.addAttribute("blogEntity",blogEntity);
        return "admin/blogDetail";
    }
    @RequestMapping(value = "/admin/blogs/delete/{id}",method = RequestMethod.GET)
    public String deleteBlog(@PathVariable("id")int id){
        blogRepository.delete(id);
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }
    @RequestMapping(value = "/admin/blogs/update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id")int id,ModelMap modelMap){
        BlogEntity blog=blogRepository.findOne(id);
        List<UserEntity> userList=userRepository.findAll();
        modelMap.addAttribute("blog",blog);
        modelMap.addAttribute("userList",userList);
        return "admin/updateBlog";
    }
    @RequestMapping(value = "/admin/blogs/updateP",method = RequestMethod.POST)
    public String updatePost(@ModelAttribute("blogP") BlogEntity blogEntity){
        blogRepository.updateBlog(blogEntity.getTitle(),blogEntity.getUserByUserId().getId(),blogEntity.getContent(),
                blogEntity.getPubDate(),blogEntity.getId());
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }
}
