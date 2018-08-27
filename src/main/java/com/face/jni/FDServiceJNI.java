package com.face.jni;

import com.face.bean.DetectResult;

public class FDServiceJNI {
    public final static String APP_ID = "GM7VibeEYNfsCYHBhGwEq7PBHK1tPsB37TkjneC3cP17";
    public final static String SDK_KEY = "4WXmGnqNfXmUQzu5ErxsCKZ8KuPAumPTmQ3ChVjoFCsg";

    static {
        System.load(System.getProperty("user.dir")+"/doc/libarcsoft_fsdk_face_detection.so");
    }

    private native String FSDK_FD_GetVersion();
    private native String FSDK_FD_InitEngine(String appId, String sdkKey, int memSize, int orientPriority, int scanle, int maxFaceNum);
    private native long FSDK_FD_FaceDetect(long engineaddr, byte[] imageData, int width, int height, DetectResult detectResult);
    private native int FSDK_FD_UninitEngine(long engineAddress, long memBuffAddress);


    public FDServiceJNI(){
        String result = FSDK_FD_InitEngine(APP_ID, SDK_KEY, 46, 5, 16, 5);
        System.out.println(result);
    }

    public String getVersion(){
        return FSDK_FD_GetVersion();
    }



}
