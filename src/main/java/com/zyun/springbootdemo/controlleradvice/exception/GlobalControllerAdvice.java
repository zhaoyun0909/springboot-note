package com.zyun.springbootdemo.controlleradvice.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局controller配置，捕获所有Controller中抛出的异常及初始化页面全局参数。
 * <p>
 * 具体controller比全局GlobalControllerAdvice优先级要高
 *
 * @author zhaoyun
 * @since 2018-11-30
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 全局异常捕捉处理Exception
     *
     * @param ex
     * @return Map
     */
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", "全局Exception错误代码");
        map.put("msg", ex.getMessage());
        return map;
    }

    /**
     * 全局拦截捕捉自定义异常 PorjectException.class
     * 渲染json格式返回给浏览器
     *
     * @param ex
     * @return Map
     */
    @ExceptionHandler(value = PorjectException.class)
    public Map porjectException(PorjectException ex) {
        Map map = new HashMap();
        map.put("code", "全局" + ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }

    /**
     * 全局拦截捕捉自定义异常 NullPointerException.class
     * 渲染某个页面模板返回给浏览器
     * 此处用到了freemarker模板引擎
     *
     * @param ex
     * @return ModelAndView
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView myErrorHandler(NullPointerException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("advice/error");
        modelAndView.addObject("code", "全局NullPointerException错误代码");
        modelAndView.addObject("msg", "全局" + ex.getMessage());
        return modelAndView;
    }

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("global", "我是个全局参数值");
    }
}
