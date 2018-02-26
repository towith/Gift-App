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
        FileSystemUtils.copyRecursively(File(Constant.templateRepoDir), outputDir)
    }
}
