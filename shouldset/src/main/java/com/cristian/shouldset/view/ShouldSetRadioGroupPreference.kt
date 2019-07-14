package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cristian.shouldset.R
import com.cristian.shouldset.manager.ShouldManager
import io.reactivex.disposables.CompositeDisposable

class ShouldSetRadioGroupPreference : LinearLayout, ShouldSetPreference {


    private val radioButtons: MutableList<ShouldSetRadioPreference> = mutableListOf()

    private val dividers: MutableList<ShouldSetDividerLine> = mutableListOf()

    override lateinit var key: String

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val shouldPreferenceManager: ShouldManager = ShouldManager

    var dividerColor: Int? = null
        set(value) {
            field = value
            dividers.forEach {
                it.color = value
            }
        }

    var valueLabelPair: (Map<String, String>)? = null
        set(value) {
            field = value
            this.removeAllViews()
            value?.keys?.forEach {
                val radio = ShouldSetRadioPreference(key, it, defaultValueKey, context)
                radio.backgroundColor = backgroundColor
                radio.textColor = textColor
                radio.title = value[it]
                if (value[it] != null) {
                    val divider = ShouldSetDividerLine(context)
                    divider.color = dividerColor
                    dividers.add(divider)
                    this.addView(divider, 0)
                    this.addView(radio, 0)
                }
                radioButtons.add(radio)
            }
        }

    var textColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                radioButtons.forEach {
                    it.textColor = value
                }
            }
        }

    var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                this.background = resources.getDrawable(value, context.theme)
                radioButtons.forEach {
                    it.backgroundColor = value
                }
            }
        }

    var defaultValueKey: String? = null


    constructor(key: String, defaultValueKey: String?, context: Context) : super(context) {
        this.key = key
        this.defaultValueKey = defaultValueKey
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onInit()
    }


    fun onInit() {
        View.inflate(context, R.layout.shouldset_preference_radio_group, this)
        this.orientation = VERTICAL
    }


}