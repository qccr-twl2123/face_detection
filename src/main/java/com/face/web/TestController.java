package com.face.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class TestController {

    @RequestMapping("test")
    public String test(ModelMap modelMap){
        String libraryPath = System.getProperty("java.library.path");
        String projectPath = System.getProperty("user.dir");
        modelMap.put("libraryPath",libraryPath);
        modelMap.put("projectPath",projectPath);
        String newLibraryPath = libraryPath+":"+projectPath+"/doc/";
        System.loadLibrary(newLibraryPath);
        modelMap.put("newLibraryPath",System.getProperty("java.library.path"));

        return "test";
    }
}
