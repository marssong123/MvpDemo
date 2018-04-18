//
// Created by SongMars on 2017/7/5.
//

#include "com_ssjj_androidmvpdemo_ndk_MyJni.h"

JNIEXPORT jstring JNICALL Java_com_ssjj_androidmvpdemo_ndk_MyJni_getString
        (JNIEnv *env, jobject instance){
    jclass clazz = (*env)->FindClass(env, "com/ssjj/androidmvpdemo/MainActivity");

    jmethodID methodID = (*env)->GetMethodID(env, clazz, "show", "(Ljava/lang/String;)V");

    (*env)->CallVoidMethod(env, obj, methodID, (*env)->NewStringUTF(env, "这是C调用Java中的show方法"));

    return (*env)->NewStringUTF(env,"This is a test");
}

