package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import com.cristian.shouldset.R
import com.cristian.shouldset.manager.ShouldManager
import io.reactivex.disposables.CompositeDisposable

class ShouldSetCheckBoxPreference : LinearLayout, ShouldSetPreference {


    private lateinit var mCheckBoxPreferenceTextView: TextView
    private lateinit var mCheckBoxPreference: CheckBox

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val shouldPreferenceManager: ShouldManager = ShouldManager

    override lateinit var key: String

    private var onValueChangedListener: ((value: Boolean) -> Unit)? = null

    var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                this.background = resources.getDrawable(value, context.theme)
            }
        }

    var title: String? = null
        set(value) {
            mCheckBoxPreferenceTextView.text = value
            field = value
        }

    var textColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                mCheckBoxPreferenceTextView.setTextColor(resources.getColor(value, context.theme))
                mCheckBoxPreference.setTextColor(resources.getColor(value, context.theme))
            }
        }

    constructor(key: String, context: Context) : super(context) {
        this.key = key
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onInit()
    }

    private fun onInit() {
        View.inflate(context, R.layout.shouldset_preference_checkbox, this)

        mCheckBoxPreferenceTextView = findViewById(R.id.shouldSetCheckBoxPreferenceTitle)
        mCheckBoxPreference = findViewById(R.id.shouldSetCheckBoxPreference)
        elevation = 8.0F

        setOnClickListener {
            mCheckBoxPreference.isChecked = !mCheckBoxPreference.isChecked
        }

        mCheckBoxPreference.setOnCheckedChangeListener { buttonView, isChecked ->
            onValueChangedListener?.invoke(isChecked)
            shouldPreferenceManager.putBoolean(key, mCheckBoxPreference.isChecked)
        }

        shouldPreferenceManager.getBooleanAsBehaviorSubject(key, false).subscribe {
            mCheckBoxPreference.isChecked = it
        }
    }

    fun setOnValueChangeListener(action: (Boolean) -> Unit) {
        onValueChangedListener = action
    }


}