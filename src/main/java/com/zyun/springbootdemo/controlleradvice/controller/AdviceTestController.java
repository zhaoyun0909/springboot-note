package com.zyun.springbootdemo.controlleradvice.controller;

import com.zyun.springbootdemo.controlleradvice.exception.PorjectException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试全局advice controller
 * <p>
 * 具体controller比全局GlobalControllerAdvice优先级要高
 *
 * @author zhaoyun
 * @since 2018-11-30
 */
@RestController
@RequestMapping("advice")
public class AdviceTestController {

    /**
     * 模拟系统Exception异常，返回json
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("exception错误");
    }

    /**
     * 模拟系统PorjectException异常，返回json
     *
     * @return
     * @throws PorjectException
     */
    @GetMapping("/porjectException")
    public String porjectException() throws PorjectException {
        throw new PorjectException("101", "porjectException错误");
    }

    /**
     * 模拟系统NullPointerException异常，返回html
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/nullPointerException")
    public String nullPointerException() throws NullPointerException {
        throw new NullPointerException("nullPointerException错误");
    }

    /**
     * 拦截捕捉自定义异常 PorjectException.class
     * 渲染某个页面模板返回给浏览器
     * 此处用到了freemarker模板引擎
     * <p>
     * 优先级：当前controller类里得ExceptionHandler最高，
     * 如本controller中的NullPointerException异常捕获将会走本方法，
     * 而当前Controller未捕获的而全局异常捕获类中有捕获的，将走全局捕获类
     *
     * @param ex
     * @return ModelAndView
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView myErrorHandler(NullPointerException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("advice/error");
        modelAndView.addObject("code", "NullPointerException当前Controller错误代码");
        modelAndView.addObject("msg", ex.getMessage());
        return modelAndView;
    }

    /**
     * 返回页面，测试是否能获取全局变量参数
     *
     * @return
     */
    @GetMapping("/getPage")
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("advice/page");
        return modelAndView;
    }
}
