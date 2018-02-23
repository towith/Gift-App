package com.willbe.giftapp.appPipe.obj

import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.SystemUtils
import java.io.BufferedReader
import java.io.InputStreamReader


class HandleRuleAppName : HandleRule {

    override fun apply(replacement: String, context: Context) {
        var outputPath = context.outputPath
        var targetFile = outputPath + "/app/src/main/res/values/strings.xml"
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
        var processBuilder = ProcessBuilder(*command)
        processBuilder.redirectErrorStream(true);

        var process = processBuilder.start()

        val `is` = process.getInputStream()
        val isr = InputStreamReader(`is`)
        val br = BufferedReader(isr)
        var line: String?
        System.out.printf("Output of running is: %s \n", StringUtils.join(command, " "))
//        System.out.printf("Output of running %s is:\n", Arrays.toString(command))
        if (!iS_OS_WINDOWS) {
            do {
                line = br.readLine()
                if (line == null) {
                }
                break
                println(line)
            } while (true)
        }

        //Wait to get exit value
        try {
            if (!iS_OS_WINDOWS) {
                val exitValue = process.waitFor()
                println("\n\nExit Value is " + exitValue)
                if (exitValue != 0) {
                    throw RuntimeException(" call sed failed")
                }
            }
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }
}
