package com.willbe.giftapp.appPipe.obj

import com.willbe.giftapp.appPipe.getContext

class SubStringHandler : Handler {

    var ruleList: List<HandleRule>? = null

    init {
        ruleList = mutableListOf(HandleRuleAppName())
    }

    override fun doHandle(context: Context) {
        for (handleRule in ruleList!!) {
            walkRule(handleRule, getContext().get().replacement)
        }
    }

    private fun walkRule(handleRule: HandleRule, replacement: String) {
        handleRule.apply(replacement, getContext().get())
    }

}
