package org.yakupyazar.translateapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.cloud.translate.Translate
import java.util.*

class MainActivity : AppCompatActivity(),MainActivityUtilsInterface.View{

    private lateinit var tvTranslatedText: TextView
    private lateinit var btnTranslate: Button
    private lateinit var sourceLanguageSpinner: Spinner
    private lateinit var targetLanguageSpinner: Spinner
    private lateinit var editText :EditText
    private lateinit var mPresenter: MainActivityPresenter
    private var translate: Translate? = null
    var targetLanguage:String = ""
    var sourceLanguage:String = ""
    val languageNames: MutableList<String> = ArrayList()
    private var languageCodes: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dateCalculationManager = TranslationManager()
        this.mPresenter = MainActivityPresenter(dateCalculationManager)
        this.mPresenter.setView(this)
        this.mPresenter.created()
        translate = Utils.getTranslateService(this);
        spinnerIntegration()
    }

    override fun bindViews() {
        this.tvTranslatedText = findViewById(R.id.tv_translated_text)
        this.btnTranslate = findViewById(R.id.btn_translate)
        this.editText =findViewById(R.id.editText)
        this.sourceLanguageSpinner = findViewById(R.id.spinnerSource)
        this.targetLanguageSpinner = findViewById(R.id.spinnerTarget)
    }

    override fun initClickListeners() {
        this.btnTranslate.setOnClickListener {
                this.mPresenter.onShowTranslateButtonClick(this, sourceLanguage, targetLanguage, editText.text.toString())
        }


    }

    override fun showTranslatedText(text: String) {
        // Burada Language Detection İşlemi  Yapıldı
        this.tvTranslatedText.text = text + "\n" +" Girilen dil muhtemelen =" +  translate!!.detect(editText.text.toString()).language;
    }

    private fun spinnerIntegration() {
        for (i in 0..translate!!.listSupportedLanguages().size-1) {
            languageNames.add(i,translate!!.listSupportedLanguages().get(i).name)
            languageCodes.add(i,translate!!.listSupportedLanguages().get(i).code)
        }
        val adapterSources = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, languageNames)
        adapterSources.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.sourceLanguageSpinner.adapter= adapterSources
        val adapterTargets = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, languageNames)
        adapterTargets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.targetLanguageSpinner.adapter=adapterTargets
        this.sourceLanguageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                sourceLanguage = languageCodes[pos]
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }

        }
        this.targetLanguageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                targetLanguage = languageCodes[pos]
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }
        }
    }

}

