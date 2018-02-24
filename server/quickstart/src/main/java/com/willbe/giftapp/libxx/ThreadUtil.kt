package com.willbe.giftapp.libxx

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ThreadUtil(private val inputStream: InputStream, private val list: MutableList<String>) : Runnable {
    // 设置读取的字符编码
    private val character = "GB2312"

    fun start() {
        val thread = Thread(this)
        thread.isDaemon = true//将其设置为守护线程
        thread.start()
    }

    override fun run() {
        var br: BufferedReader? = null
        try {
            br = BufferedReader(InputStreamReader(inputStream, character))
            var line: String? = null
            do {
                line = br.readLine()
                if (line != null) {
                    list.add(line)
                    println(line)
                } else {
                    break
                }
            } while (true)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                //释放资源
                inputStream.close()
                br!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

}