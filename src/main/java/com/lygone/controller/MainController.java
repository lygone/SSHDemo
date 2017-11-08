package com.lygone.controller;

import com.lygone.model.UserEntity;
import com.lygone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 讲解：

 自动装配：相当于数据库操作的极简化，只要定义了就可以直接进行数据库操作，不用再去管开启连接、关闭连接等问题

 找到所有记录：使用JpaRepository的默认方法findAll()。

 modelMap：用于将controller方法里面的参数传递给所需的jsp页面，以进行相关显示。
 * Created by Administrator on 2017/7/6.
 */
@Controller
public class MainController {
    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
// 查询user表中所有记录
        List<UserEntity> userList = userRepository.findAll();
// 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);
// 返回pages目录下的admin/users.jsp页面
        return "admin/users";
    }
    @RequestMapping(value = "/admin/users/add",method = RequestMethod.GET)
    public String addUser(){
        return "admin/adduser";
    }
    @RequestMapping(value = "/admin/users/addP",method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user")UserEntity userEntity){
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
// 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象
// 数据库中添加一个用户，该步暂时不会刷新缓存
//userRepository.save(userEntity);
// 数据库中添加一个用户，并立即刷新缓存
        //admin/users/add请求：get请求转到添加用户页面

               // /admin/users/addP请求：post请求收集数据并存库

        //@ModelAttribute注解：收集post过来的数据（在此，相当于post过来了一整个userEntity，不用一个一个地取）

        //save()和saveAndFlush()：save()方法处理完毕后，数据依然在缓冲区未写入数据库，使用saveAndFlush()可以立即刷新缓冲区，写入数据库

       // redirect:/admin/users：这里使用重定向，可以让该方法重定向访问一个请求，ruturn之后，将跳转到 :/admin/users 所访问的页面。
        userRepository.saveAndFlush(userEntity);
        return "redirect:/admin/users";
// 重定向到用户管理页面，方法为 redirect:url
    }
    // 查看用户详情
// @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
// 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "/admin/users/show/{id}",method = RequestMethod.GET)
    public String showUser(@PathVariable("id")Integer userId,ModelMap modelMap){
        // 找到userId所表示的用户
        UserEntity userEntity=userRepository.findOne(userId);
        // 传递给请求页面
        modelMap.addAttribute("user",userEntity);
        return "admin/userDetail";
    }
    @RequestMapping(value = "/admin/users/update/{id}",method = RequestMethod.GET)
    public String updateUser(@PathVariable("id")Integer userId,ModelMap modelMap){
        UserEntity userEntity=userRepository.findOne(userId);
        modelMap.addAttribute("user",userEntity);
        return "admin/updateUser";
    }
    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user){
        userRepository.updateUser(user.getNickname(),user.getFirstName(),user.getLastName(),user.getPassword(),user.getId());
        userRepository.flush();
        return "redirect:/admin/users";
    }
    @RequestMapping(value = "/admin/users/delete/{id}",method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId){
        userRepository.delete(userId);
        userRepository.flush();
        return "redirect:/admin/users";
    }
}
