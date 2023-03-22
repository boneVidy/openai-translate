package com.github.bonevidy.openaitranslate.api
import com.plexpt.chatgpt.ChatGPT
abstract class API {
    companion object {
        private var chatGPT: ChatGPT? = null

        @JvmStatic
        fun init(): ChatGPT {
            if (chatGPT == null) {
                chatGPT = ChatGPT.builder()
                    .apiKey("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")
                    .apiHost("https://api.openai.com/")
                    .build()
                    .init()
            }
            return chatGPT!!
        }
    }
}