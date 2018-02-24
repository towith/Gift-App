package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.appPipe.getContext
import com.willbe.giftapp.libxx.procCall
import org.junit.Test

class GradleHandlerTest : HandlerTest(){

    @Test
    fun doHandle() {
        GradleHandler().doHandle(context)
    }

    @Test
    fun rightHandle() {
        procCall(arrayOf("sed", "-i", "jjljlj"), false, getContext().get().workingDir)
    }
}


