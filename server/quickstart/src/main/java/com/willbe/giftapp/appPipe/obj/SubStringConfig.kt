package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.dto.AppWidgetDTO
import org.springframework.boot.json.GsonJsonParser

//import org.springframework.boot.json.GsonJsonParser

class SubStringConfig(appWidgetDTO: AppWidgetDTO) : WidgetConfig(appWidgetDTO) {
    lateinit var placeHolder: String;
    lateinit var targetFile: String;

    init {
        resolveRule(appWidgetDTO.rule)
    }

    private fun resolveRule(rule: String?) {
        var gsonJsonParser = GsonJsonParser()
        var parseMap = gsonJsonParser.parseMap(rule)
        var placeHolder = parseMap.get("placeHolder")
        this.placeHolder = placeHolder as String
        this.targetFile = parseMap.get("targetFile") as String
    }
}