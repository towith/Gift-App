package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.Constant
import org.springframework.util.FileSystemUtils
import java.io.File
import java.io.IOException

class PrepareHandler : Handler {
    @Throws(IOException::class)
    override fun doHandle(context: Context) {
        copyWorkSpace(context)
    }

    @Throws(IOException::class)
    private fun copyWorkSpace(context: Context) {
        var outputDir = File(context.outputPath!!)
        outputDir.deleteRecursively();
        outputDir.mkdir()

        var file = File(Constant.templateRepoDir + File.separator + context.app.templatePath)
        if (file.isDirectory) {

        } else {
            throw RuntimeException("Template is not directory")
        }
        var list = file.listFiles()
        list.forEach() {
            FileSystemUtils.copyRecursively(it, File(outputDir.absolutePath + File.separator + it.name))
        }
    }
}
