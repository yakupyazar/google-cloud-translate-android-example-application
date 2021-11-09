package org.yakupyazar.translateapplication

import android.content.Context

interface MainActivityUtilsInterface {

    interface View {

        fun showTranslatedText(text: String)

        fun bindViews()

        fun initClickListeners()

    }

    interface Presenter {

        fun setView(view: View)

        fun created()

        fun onShowTranslateButtonClick(ctx:Context,sourceLanguage: String,targetLanguage: String,text:String)

    }

}