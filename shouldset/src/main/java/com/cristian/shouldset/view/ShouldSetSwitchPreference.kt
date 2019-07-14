package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import com.cristian.shouldset.R
import com.cristian.shouldset.manager.ShouldManager
import io.reactivex.disposables.CompositeDisposable

class ShouldSetSwitchPreference : LinearLayout, ShouldSetPreference {


    private lateinit var mSwitchPreferenceTextView: TextView
    private lateinit var mSwitchPreference: Switch

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
            mSwitchPreferenceTextView.text = value
            field = value
        }

    var textColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                mSwitchPreferenceTextView.setTextColor(resources.getColor(value, context.theme))
                mSwitchPreference.setTextColor(resources.getColor(value, context.theme))
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
        View.inflate(context, R.layout.shouldset_preference_switch, this)
        mSwitchPreferenceTextView = findViewById(R.id.shouldSetSwitchPreferenceTitle)
        mSwitchPreference = findViewById(R.id.shouldSetSwitchPreference)
        elevation = 8.0F

        setOnClickListener {
            mSwitchPreference.isChecked = !mSwitchPreference.isChecked
        }

        mSwitchPreference.setOnCheckedChangeListener { buttonView, isChecked ->
            onValueChangedListener?.invoke(isChecked)
            shouldPreferenceManager.putBoolean(key, mSwitchPreference.isChecked)
        }

        shouldPreferenceManager.getBooleanAsBehaviorSubject(key, false).subscribe {
            mSwitchPreference.isChecked = it
        }
    }

    fun setOnValueChangeListener(action: (Boolean) -> Unit) {
        onValueChangedListener = action
    }


}