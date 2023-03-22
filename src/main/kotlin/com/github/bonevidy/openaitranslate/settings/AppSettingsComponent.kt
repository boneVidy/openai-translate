package com.github.bonevidy.openaitranslate.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel
import org.jetbrains.annotations.NotNull;


/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    public var panel: JPanel
        get() {
            return field
        }
    public val apiKeyText = JBTextField()
        get() = field
//    private val myIdeaUserStatus = JBCheckBox("Do you use IntelliJ IDEA? ")

    init {
        panel = FormBuilder.createFormBuilder()
                .addLabeledComponent(JBLabel("Enter OpenAi Api Key: "), apiKeyText, 1, false)
//                .addComponent(myIdeaUserStatus, 1)
                .addComponentFillVertically(JPanel(), 0)
                .panel
    }

    val preferredFocusedComponent: JComponent
        get() = apiKeyText

    @get:NotNull
    var userNameText: String?
        get() = apiKeyText.text
        set(newText) {
            apiKeyText.text = newText
        }
//    var ideaUserStatus: Boolean
//        get() = myIdeaUserStatus.isSelected
//        set(newStatus) {
//            myIdeaUserStatus.isSelected = newStatus
//        }
}