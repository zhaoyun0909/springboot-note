package com.zyun.springbootdemo.helloworld.controller;

import org.springframework.web.bind.annotation.*;

/**
 * hello world controller
 *
 * @author zhaoyun
 * @since 2018-11-30
 */
@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @GetMapping("/hello")
    //与下面等价
    //@RequestMapping(method = RequestMethod.GET,path = "hello")
    public String helloGet() {
        return "hello world get!";
    }

    @PostMapping("/hello")
    //与下面等价
    //@RequestMapping(method = RequestMethod.GET,path = "hello")
    public String helloPost() {
        return "hello world post!";
    }

    @DeleteMapping("/hello")
    //与下面等价
    //@RequestMapping(method = RequestMethod.DELETE,path = "hello")
    public String helloDelete() {
        return "hello world delete!";
    }

}
