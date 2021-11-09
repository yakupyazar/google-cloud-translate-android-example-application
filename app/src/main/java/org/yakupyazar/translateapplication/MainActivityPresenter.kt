package org.yakupyazar.translateapplication

import android.content.Context

class MainActivityPresenter(translationManager: TranslationManager) : MainActivityUtilsInterface.Presenter {

    private lateinit var mView: MainActivityUtilsInterface.View
    private var mTranslationManager = translationManager


    override fun setView(view: MainActivityUtilsInterface.View) {
        this.mView = view
    }

    override fun created() {
        this.mView.bindViews()
        this.mView.initClickListeners()
    }

    override fun onShowTranslateButtonClick(ctx: Context, sourceLanguage : String, targetLanguage: String,text: String) {
        val text = this.mTranslationManager.textTranslate(ctx,sourceLanguage,targetLanguage,text)
        this.mView.showTranslatedText(text)
    }

}