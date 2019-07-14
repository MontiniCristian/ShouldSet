package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.cristian.shouldset.R

class ShouldSetCategory : LinearLayout, ShouldSetView {

    private var mTitleTextView: TextView? = null

    var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) mTitleTextView?.background = resources.getDrawable(value, context.theme)
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
        mTitleTextView = findViewById<TextView>(R.id.shouldSetCategoryTitle)
    }

}