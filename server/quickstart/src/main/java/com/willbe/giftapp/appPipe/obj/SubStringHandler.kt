package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.appPipe.getContext

class SubStringHandler : Handler {

    var replacement: String = ""
    var ruleList: List<HandleRule>? = null

    init {
        ruleList = mutableListOf(HandleRuleAppName())
    }

    override fun doHandle(point: Context) {
        for (handleRule in ruleList!!) {
            walkRule(handleRule, replacement)
        }
    }

    private fun walkRule(handleRule: HandleRule, replacement: String) {
        handleRule.apply(replacement, getContext().get())
    }

}
