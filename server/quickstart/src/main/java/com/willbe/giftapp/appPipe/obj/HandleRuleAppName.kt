package com.willbe.giftapp.appPipe.obj

import java.io.File

class HandleRuleAppName : HandleRule {

    override fun apply(replacement: String, context: Context) {
        var outputPath = context.outputPath
        var targetFile = outputPath + File.separator + "values/strings.xml"
        replace(targetFile, "#___APP_NAME___#", replacement)
    }

    private fun replace(targetFile: String, token: String, replacement: String) {
        var command = listOf<String>("sed", "-i", "'s*${token}*${replacement}*'", targetFile)
        ProcessBuilder(command).start()
    }
}
