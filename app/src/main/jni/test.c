//
// Created by SongMars on 2017/7/5.
//

#include "com_ssjj_androidmvpdemo_ndk_MyJni.h"

JNIEXPORT jstring JNICALL Java_com_ssjj_androidmvpdemo_ndk_MyJni_getString
        (JNIEnv *env, jobject instance){

    return (*env)->NewStringUTF(env,"This is a test");
}

