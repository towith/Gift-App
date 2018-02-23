package com.willbe.giftapp.appPipe.obj

import com.jaxio.celerio.output.FileUtil
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.springframework.util.FileCopyUtils
import org.springframework.util.FileSystemUtils
import java.io.File
import java.io.IOException
import java.nio.file.Files

class PrepareHandler : Handler {
    @Throws(IOException::class)
    override fun doHandle(context: Context) {
        copyWorkSpace(context)
    }

    @Throws(IOException::class)
    private fun copyWorkSpace(context: Context) {
        var outputDir = File(context.outputPath!!)
        outputDir.deleteRecursively();
        FileSystemUtils.copyRecursively(File(Conversion.templateDir), outputDir)
    }
}
