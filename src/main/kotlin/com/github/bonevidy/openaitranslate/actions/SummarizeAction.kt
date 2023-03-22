package com.github.bonevidy.openaitranslate.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import com.plexpt.chatgpt.ChatGPT
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import javax.swing.SwingUtilities

class SummarizeAction : AnAction() ,CoroutineScope {
     private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val viewModel = SummarizeViewModel()

    override fun actionPerformed(e: AnActionEvent): Unit = runBlocking{
        val editor: Editor? = e.getData(CommonDataKeys.EDITOR)
        val selectedText = editor?.selectionModel?.selectedText
        if (selectedText.isNullOrEmpty()) {
            Messages.showMessageDialog("请先选择一些文字", "open ai translator", Messages.getInformationIcon())
            return@runBlocking
        }
        val summarize = async{viewModel.summarize(selectedText)}
        val str = summarize.await()
        SwingUtilities.invokeLater {
            Messages.showMessageDialog(str, "open ai translator", Messages.getInformationIcon())
        }
    }

    class SummarizeViewModel {

        private var chatGPT: ChatGPT? = null

       suspend fun summarize(text: String): String? {
           return getChatGPT()?.chat("帮我总结这些文字")
        }

        private  fun getChatGPT(): ChatGPT? {
            if (chatGPT == null) {
                chatGPT = ChatGPT
                        .builder()
                        .apiKey("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")
                        .apiHost("https://api.openai.com/")
                        .build()
                        .init()
            }
            return chatGPT
        }
    }


}