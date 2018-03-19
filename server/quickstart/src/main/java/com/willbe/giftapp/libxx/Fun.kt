package com.willbe.giftapp.libxx

import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.SystemUtils
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun procCall(command: Array<String>,blocked: Boolean, workingDir: String) {
    CommandUtil().executeCommand(command, blocked,File(workingDir))
}

fun procCall_(command: Array<String>, blocked: Boolean, workingDir: String) {
    var processBuilder = ProcessBuilder(*command)
    processBuilder.redirectErrorStream(true);
    var workingDirFile = File(workingDir)
    processBuilder.directory(workingDirFile)
    var process = processBuilder.start()


//    System.setProperty("user.dir", workingDirFile.absolutePath)
//    val process = Runtime.getRuntime().exec(command)
//    var errorStream = process.errorStream

//    for (i in 0 until errorStream.available()) {
//        println("" + errorStream.read())
//    }

    val `is` = process.getInputStream()
    val isr = InputStreamReader(`is`)
    val br = BufferedReader(isr)
    var line: String?
    var joinCmd = StringUtils.join(command, " ")
    System.out.printf("joinCmd is: %s \n", joinCmd)
    if (!SystemUtils.IS_OS_WINDOWS) {
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
        if (blocked) {
            process.waitFor()
            val exitValue = process.exitValue()
            println("\n\nExit Value is " + exitValue)
            if (exitValue != 0) {
                throw RuntimeException(" call " + joinCmd + " failed on ${workingDirFile.absolutePath}")
            }
        }
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}
