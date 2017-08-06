package cn.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
public class IndexController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
