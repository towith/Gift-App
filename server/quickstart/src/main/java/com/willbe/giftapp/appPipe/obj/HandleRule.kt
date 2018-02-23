package com.willbe.giftapp.appPipe.obj

interface HandleRule {
    fun apply(replacement: String, context: Context)
}
