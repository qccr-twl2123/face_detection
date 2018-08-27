package com.face.jniutil;

import org.apache.http.util.Asserts;

import java.lang.reflect.Field;

public class GetDownloadIDUtil {

    static{
        try{
             setLibraryPath("D:\\workspace\\git\\face_detection\\doc");
             System.loadLibrary("libarcsoft_fsdk_face_detection");
        }catch(Exception e){
            System.err.println("Native code library failed to load.\n" + e); System.exit(1);
        }
    }

    public static String getDownLoadId(String mac){
        GetDownloadID test = new GetDownloadID();
        String downLoadId = test.getDownloadID(mac); return downLoadId;
    }
    public static void setLibraryPath(String path) throws Exception {
        System.setProperty("java.library.path", path);
        //set sys_paths to null final
        Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        sysPathsField.setAccessible(true); sysPathsField.set(null, null);
    }
    public static void main(String[] args){
        //-Djava.library.path="/Users/mark/mms/src/main/java/com/fxmms/common/jniutil"
        // /Users/mark/mms/src/main/java/com/fxmms/common/jniutil
        System.out.println(System.getProperty("java.library.path"));
        String mac = " 98-22-EF-E4-3F-8F";
        Asserts.check(mac!=null,"mac  null");
        GetDownloadID test = new GetDownloadID();
        System.out.println(test.getDownloadID(mac));
    }

}
