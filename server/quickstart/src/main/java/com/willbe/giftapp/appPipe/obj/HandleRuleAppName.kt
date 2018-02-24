package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.appPipe.getContext
import com.willbe.giftapp.procCall
import org.apache.commons.lang.SystemUtils


class HandleRuleAppName : HandleRule {

    override fun apply(replacement: String, context: Context) {
        var targetFile = "app/src/main/res/values/strings.xml"
        replace(targetFile, "#___APP_NAME___#", replacement)
    }

    private fun replace(targetFile: String, token: String, replacement: String) {
        var command: Array<String>
        var iS_OS_WINDOWS = SystemUtils.IS_OS_WINDOWS
        if (iS_OS_WINDOWS) {
            command = arrayOf("u", "sed", "-i", "'s*${token}*${replacement}*'", targetFile)
        } else {
            command = arrayOf("sed", "-i", "'s*${token}*${replacement}*'", targetFile)
        }
        procCall(command, iS_OS_WINDOWS, getContext().get().workingDir)
    }

}
