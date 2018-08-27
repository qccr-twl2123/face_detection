package com.face.web;

import com.face.jni.FDServiceJNI;
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
        String newLibraryPath =  projectPath+"/doc/libarcsoft_fsdk_face_detection.so";
        System.load(newLibraryPath);
        modelMap.put("newLibraryPath",System.getProperty("java.library.path"));
        FDServiceJNI fdServiceJNI = new FDServiceJNI();
        modelMap.put("version",fdServiceJNI.getVersion());
        modelMap.put("path",System.getProperty("user.dir")+"/doc/libarcsoft_fsdk_face_detection.so");
        return "test";
    }

    @RequestMapping("test1")
    public String test1(ModelMap modelMap){
//        System.loadLibrary("libarcsoft_fsdk_face_detection");
        modelMap.put("path",System.getProperty("java.library.path"));
        modelMap.put("path1", modelMap.put("path1",System.getProperty("user.dir")+"/doc/libarcsoft_fsdk_face_detection.so"));
        return "test1";
    }


    /**
     * Sets the java library path to the specified path
     *
     * @param path the new library path
     * @throws Exception
     */
    public static void setLibraryPath(String path) throws Exception {
        System.setProperty("java.library.path", path);
        //set sys_paths to null
        final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        sysPathsField.setAccessible(true);
        sysPathsField.set(null, null);
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
