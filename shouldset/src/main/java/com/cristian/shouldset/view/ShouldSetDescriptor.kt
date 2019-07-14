package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.cristian.shouldset.R

class ShouldSetDescriptor : LinearLayout, ShouldSetView {

    private lateinit var mShouldSetDescriptorContentTextView: TextView

    var description: String? = null
        set(value) {
            field = value
            mShouldSetDescriptorContentTextView.text = value
        }

    var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) mShouldSetDescriptorContentTextView.background =
                resources.getDrawable(value, context.theme)
        }

    var textColor: Int? = null
        set(value) {
            field = value
            if (value != null) mShouldSetDescriptorContentTextView.setTextColor(
                resources.getColor(
                    value,
                    context.theme
                )
            )
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
        View.inflate(context, R.layout.shouldset_preference_descriptor, this)
        mShouldSetDescriptorContentTextView = findViewById(R.id.shouldSetDescriptorContentTextView)
    }

}