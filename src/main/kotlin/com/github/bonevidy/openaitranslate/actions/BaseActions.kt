package com.github.bonevidy.openaitranslate.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseActions:AnAction(), CoroutineScope {
    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default
    override fun actionPerformed(e: AnActionEvent) {
        TODO("Not yet implemented")
    }


}