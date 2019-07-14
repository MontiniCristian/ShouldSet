package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.cristian.shouldset.R

class ShouldSetDividerSpace : LinearLayout {

    var color: Int? = null
        set(value) {
            field = value
            if (value != null) this.background = resources.getDrawable(value, context.theme)
        }

    constructor(context: Context) : super(context) {
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun onInit() {
        View.inflate(context, R.layout.shouldset_divider_space, this)
    }

}