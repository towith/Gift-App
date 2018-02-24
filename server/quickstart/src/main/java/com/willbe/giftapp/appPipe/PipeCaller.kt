package com.willbe.giftapp.appPipe

import com.willbe.giftapp.appPipe.obj.*
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.*

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
        this.handlers.add(GradleHandler())
    }

    @Throws(IOException::class)
    fun call() {
        for (handler in handlers) {
            println("start handler:" + handler::class)
            var start = System.currentTimeMillis()
            handler.doHandle(threadContext.get())
            var end = System.currentTimeMillis()
            print("time cost:${(end - start) / 1000} s")
            println()
        }
    }
}
