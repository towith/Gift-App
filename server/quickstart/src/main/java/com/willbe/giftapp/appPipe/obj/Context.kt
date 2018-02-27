package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.dto.App_DTO

class Context(var outputPath: String, var workingDir: String) {
    lateinit var app: App_DTO;
}
