package com.willbe.giftapp.appPipe.obj

import org.junit.Test

class SubStringHandlerTest : HandlerTest() {

    @Test
    fun doHandle() {
        SubStringHandler().doHandle(context)
    }
}