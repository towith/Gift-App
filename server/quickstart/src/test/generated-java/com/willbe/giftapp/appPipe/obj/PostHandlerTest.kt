package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.Application
import com.willbe.giftapp.BaseTest
import org.junit.Assert.assertNotNull
import org.junit.Test

class PostHandlerTest : BaseTest() {

    @Test
    fun setApplicationContext() {
        assertNotNull(Application.applicationContext)
    }
}