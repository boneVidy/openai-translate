package com.github.bonevidy.openaitranslate.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable
import javax.swing.JComponent


/**
 * Provides controller functionality for application settings.
 */
class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null

//    override val preferredFocusedComponent: JComponent?
//        get() = mySettingsComponent?.preferredFocusedComponent
    override fun getPreferredFocusedComponent(): JComponent {
        return mySettingsComponent!!.preferredFocusedComponent
    }
    @Nullable
    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings: AppSettingsState = AppSettingsState.instance
        val apiKey = mySettingsComponent?.apiKeyText?.text
        return apiKey != null && apiKey != settings.apiKey
    }


    override fun apply() {
        val settings: AppSettingsState = AppSettingsState.instance
        settings.apiKey = mySettingsComponent?.apiKeyText?.text ?: ""
    }

    override fun reset() {
        val settings: AppSettingsState = AppSettingsState.instance
        mySettingsComponent?.userNameText = settings.apiKey
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "OpenAi Translator Settings"
    }
}