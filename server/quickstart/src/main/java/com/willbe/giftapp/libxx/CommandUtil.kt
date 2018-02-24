package com.willbe.giftapp.libxx

import org.apache.commons.lang.StringUtils
import java.io.File
import java.util.*

class CommandUtil {
    // 保存进程的输入流信息
    private val stdoutList = ArrayList<String>()
    // 保存进程的错误流信息
    private val erroroutList = ArrayList<String>()

    fun executeCommand(command: Array<String>, blocked: Boolean, workingDir: File) {
        // 先清空
        stdoutList.clear()
        erroroutList.clear()

        var p: Process? = null
//            p = Runtime.getRuntime().exec(command)
        println(StringUtils.join(command, " "))
        var processBuilder = ProcessBuilder(*command)
        if (workingDir != null) {
            processBuilder.directory(workingDir)
        }
        p = processBuilder.start()
        // 创建2个线程，分别读取输入流缓冲区和错误流缓冲区
        val stdoutUtil = ThreadUtil(p!!.inputStream, stdoutList)
        val erroroutUtil = ThreadUtil(p.errorStream, erroroutList)
        //启动线程读取缓冲区数据
        stdoutUtil.start()
        erroroutUtil.start()

        if (!blocked) {
            p.waitFor()
            var exitValue = p.exitValue()
            if (exitValue != 0) {
                var error = Error(" call failed:[" + exitValue + "]")
                error.printStackTrace()
                throw error
            }
        }
    }

    fun getStdoutList(): List<String> {
        return stdoutList
    }

    fun getErroroutList(): List<String> {
        return erroroutList
    }

}