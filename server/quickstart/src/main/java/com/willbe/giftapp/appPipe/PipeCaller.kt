package com.willbe.giftapp.appPipe

import com.willbe.giftapp.WidgetType
import com.willbe.giftapp.appPipe.obj.*
import com.willbe.giftapp.dto.AppWidgetDTO
import com.willbe.giftapp.dto.App_DTO
import java.io.IOException
import java.util.*

val threadContext: ThreadLocal<Context> = ThreadLocal()

fun setContext(context: Context) {
    threadContext.set(context)
}

fun getContext(): ThreadLocal<Context> {
    return threadContext;
}


class PipeCaller(app: App_DTO, widgetsConfig: List<AppWidgetDTO>) {
    var handlers: MutableList<Handler> = ArrayList()
    //    var  context: Context = context;
    var app: App_DTO;

    init {
        this.app = app;
        addHandler(widgetsConfig)
    }

    private fun addHandler(widgetsConfig: List<AppWidgetDTO>) {
        this.handlers.add(PrepareHandler())
        for (appWidgetDTO in widgetsConfig) {
            if (WidgetType.placeHolderText.value.equals(appWidgetDTO.type)) {
                var widgetConfig = SubStringConfig(appWidgetDTO)
                this.handlers.add(SubStringHandler(widgetConfig))
            }
        }
        this.handlers.add(GradleHandler())
    }

    @Throws(IOException::class)
    fun call() {
        var uuid: String = UUID.randomUUID().toString()
        var context = Context(uuid, uuid)
        setContext(context)

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
