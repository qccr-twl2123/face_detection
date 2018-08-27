package com.face.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.util.Arrays;

@Controller
public class TestController {

    @RequestMapping("test")
    public String test(ModelMap modelMap){
        String libraryPath = System.getProperty("java.library.path");
        String projectPath = System.getProperty("user.dir");
        modelMap.put("libraryPath",libraryPath);
        modelMap.put("projectPath",projectPath);
        String newLibraryPath = libraryPath+":"+projectPath+"/doc/";
        try {
            addLibraryPath(projectPath+"/doc/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.put("newLibraryPath",System.getProperty("java.library.path"));

        return "test";
    }

    /**
     * Adds the specified path to the java library path
     *
     * @param pathToAdd the path to add
     * @throws Exception
     */
    public static void addLibraryPath(String pathToAdd) throws Exception{
        final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
        usrPathsField.setAccessible(true);
        //get array of paths
        final String[] paths = (String[])usrPathsField.get(null);
        //check if the path to add is already present
        for(String path : paths) {
            if(path.equals(pathToAdd)) {
                return;
            }
        }
        //add the new path
        final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
        newPaths[newPaths.length-1] = pathToAdd;
        usrPathsField.set(null, newPaths);
    }
}
