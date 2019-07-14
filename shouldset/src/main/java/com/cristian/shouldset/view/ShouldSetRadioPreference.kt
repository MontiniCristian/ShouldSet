package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.cristian.shouldset.R
import com.cristian.shouldset.manager.ShouldManager
import io.reactivex.disposables.CompositeDisposable

class ShouldSetRadioPreference : LinearLayout, ShouldSetPreference {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override val shouldPreferenceManager: ShouldManager = ShouldManager
    override lateinit var key: String

    private lateinit var mRadioPreferenceTextView: TextView
    private lateinit var mRadioPreference: RadioButton

    private lateinit var value: String

    private var defaultValueKey: String? = null

    var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                this.background = resources.getDrawable(value, context.theme)
                mRadioPreferenceTextView.background = resources.getDrawable(value, context.theme)
            }
        }

    var title: String? = null
        set(value) {
            mRadioPreferenceTextView.text = value
            field = value
        }

    var textColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                mRadioPreferenceTextView.setTextColor(resources.getColor(value, context.theme))
                mRadioPreference.setTextColor(resources.getColor(value, context.theme))
            }
        }

    constructor(key: String?, value: String, defaultValueKey: String?, context: Context) : super(context) {
        if (key == null) throw NullPreferenceKeyException(this)
        this.key = key
        this.value = value
        this.defaultValueKey = defaultValueKey
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onInit()
    }

    private fun onInit() {
        View.inflate(context, R.layout.shouldset_preference_radio, this)
        mRadioPreferenceTextView = findViewById(R.id.shouldSetRadioPreferenceTitle)
        mRadioPreference = findViewById(R.id.shouldSetRadioPreference)

        mRadioPreference.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) shouldPreferenceManager.putString(key, value)
        }

        elevation = 8.0F

        shouldPreferenceManager.getStringAsBehaviorSubject(
            key,
            defaultValueKey ?: throw NullDefaultValueException(this)
        )
            .subscribe {
                mRadioPreference.isChecked = (it == value)
            }

        this.setOnClickListener {
            if (!mRadioPreference.isChecked)
                mRadioPreference.isChecked = !mRadioPreference.isChecked
        }
    }

}