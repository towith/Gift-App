package com.willbe.giftapp.dto

import com.willbe.giftapp.appPipe.PipeCaller
import org.springframework.stereotype.Service

@Service
class PipeLineService {


    fun call(widgetsConfig: List<AppWidgetDTO>) {
        if (widgetsConfig == null || widgetsConfig.size == 0) {
            return
        }
        var app = widgetsConfig.get(0).app
        for (appWidgetDTO in widgetsConfig) {
            PipeCaller(app, widgetsConfig).call()
        }
    }
}
