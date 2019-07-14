package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.cristian.shouldset.R

class ShouldSetBottomMultiple : LinearLayout, ShouldSetView {

    private var mOnClickListener: (() -> Unit)? = null

    var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                mTitleTextView?.background = resources.getDrawable(value, context.theme)
                this.background = resources.getDrawable(value, context.theme)
            }
        }

    var title: String? = null
        set(value) {
            mTitleTextView?.text = value
            field = value
        }

    var textColor: Int? = null
        set(value) {
            field = value
            if (value != null) mTitleTextView?.setTextColor(resources.getColor(value, context.theme))
        }

    var dividerColor: Int? = null

    var keyLabelPair: (Map<String, String>)? = null


    private var mTitleTextView: TextView? = null

    constructor(context: Context) : super(context) {
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onInit()
    }

    private fun onInit() {
        View.inflate(context, R.layout.shouldset_preference_multiple, this)
        mTitleTextView = findViewById(R.id.shouldSetMultiplePreferenceTitle)
        setOnClickListener {
            mOnClickListener?.invoke()
        }

        elevation = 8.0F
    }

    fun setOnSelectedListener(action: () -> Unit) {
        mOnClickListener = action
    }
}