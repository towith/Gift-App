package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.appPipe.setContext

open class HandlerTest() {
    var context: Context

    init {
        context = Context("testdirxx","testdirxx")
        setContext(context)
    }
}