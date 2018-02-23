package com.willbe.giftapp.appPipe

import com.willbe.giftapp.BaseTest
import com.willbe.giftapp.appPipe.obj.Context
import org.junit.Test
import java.io.IOException
import javax.inject.Inject

class PipeCallerTest : BaseTest() {

    @Inject
    private val caller: PipeCaller? = null

    @Test
    @Throws(IOException::class)
    fun call() {
        var context = Context("testdirxx")
        context.replacement="gift from haven"
        setContext(context)
        caller!!.call()
    }
}