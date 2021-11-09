package org.yakupyazar.translateapplication

import android.content.Context
import com.google.cloud.translate.Translate

class TranslationManager {
    private var translate: Translate? = null

    fun textTranslate(ctx: Context, sourceLanguage: String, targetLanguage: String, text: String): String {
        translate = Utils.getTranslateService(ctx);
        //Burada dil combobox'tan seçilecek, id ve name isimleri ayrı ayrı alınıp,TranslateOption.sourceLanguage() ve TranslateOption.targetLanguage()
        //metotlarına verilecek
       try {
           val translation = translate!!.translate(text, Translate.TranslateOption.sourceLanguage(sourceLanguage),Translate.TranslateOption.targetLanguage(targetLanguage), Translate.TranslateOption.model("base"))
           return translation.translatedText
       }
       catch (e:Exception)
       {
           return "Hata!"
       }

    }
}