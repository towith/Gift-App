package com.willbe.giftapp.appPipe

import com.willbe.giftapp.appPipe.obj.Handler
import com.willbe.giftapp.appPipe.obj.Context
import com.willbe.giftapp.appPipe.obj.PrepareHandler
import com.willbe.giftapp.appPipe.obj.SubStringHandler
import org.springframework.stereotype.Component

import java.io.IOException
import java.util.ArrayList

val threadContext: ThreadLocal<Context> = ThreadLocal()

fun setContext(context: Context) {
    threadContext.set(context)
}

fun getContext(): ThreadLocal<Context> {
    return threadContext;
}

@Component
class PipeCaller() {
    var handlers: MutableList<Handler> = ArrayList()
    //    var  context: Context = context;

    init {
        this.handlers.add(PrepareHandler())
        this.handlers.add(SubStringHandler())
    }

    @Throws(IOException::class)
    fun call() {
        for (handler in handlers) {
            handler.doHandle(threadContext.get())
        }
    }
}
