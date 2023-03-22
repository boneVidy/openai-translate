package com.github.bonevidy.openaitranslate.api

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Translator:CoroutineScope {
    companion object {
        private  var instance:Translator? = null;
        @JvmStatic
        fun init(): Translator {
            if (Translator.instance == null) {
                Translator.instance = Translator()
            }
            return Translator.instance!!;
        }

        @JvmStatic
        suspend fun translate(text: String):String {
            return init().translateText(text);
        }
    }

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default


    suspend fun translateText(text: String):String {
        return withContext(Dispatchers.IO) {
            API.init().chat("$text\n请帮我翻译以上文字到中文");
        }
    }
}