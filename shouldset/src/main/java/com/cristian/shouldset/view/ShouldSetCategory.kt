package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.cristian.shouldset.R

open class ShouldSetCategory : LinearLayout {

    private var mTitleTextView: TextView? = null

    open var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) this.background = resources.getDrawable(value, context.theme)
        }

    open var title: String? = null
        set(value) {
            mTitleTextView?.text = value
            field = value
        }

    open var textColor: Int? = null
        set(value) {
            field = value
            if (value != null) mTitleTextView?.setTextColor(resources.getColor(value, context.theme))
        }


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
        View.inflate(context, R.layout.shouldset_preference_category, this)
        this.background = resources.getDrawable(backgroundColor ?: android.R.color.transparent, context.theme)
        mTitleTextView = findViewById<TextView>(R.id.shouldSetCategoryTitle)
        mTitleTextView?.text = title

    }

}