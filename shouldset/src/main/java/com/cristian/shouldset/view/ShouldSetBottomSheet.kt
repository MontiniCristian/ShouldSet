package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.cristian.shouldset.R

class ShouldSetBottomSheet : LinearLayout, ShouldSetView {
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
        View.inflate(context, R.layout.shouldset_bottom_sheet, this)
    }
}