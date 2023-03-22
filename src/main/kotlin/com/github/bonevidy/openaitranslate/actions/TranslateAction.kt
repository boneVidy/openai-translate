package com.github.bonevidy.openaitranslate.actions

import com.github.bonevidy.openaitranslate.api.Translator
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages
import kotlinx.coroutines.*
import javax.swing.SwingUtilities

class TranslateAction: BaseActions() {
    override fun actionPerformed(e: AnActionEvent)  {
        val editor: Editor? = e.getData(CommonDataKeys.EDITOR)
        editor.let {
            val selectionModel = editor!!.selectionModel
            val selectedText = selectionModel.selectedText
            if (selectedText != null) {
                launch {
                    val result = Translator.translate(selectedText)
                    SwingUtilities.invokeLater{
                        Messages.showMessageDialog(result, "open ai translator", Messages.getInformationIcon())
                    }
                }
            }

        }
    }

}