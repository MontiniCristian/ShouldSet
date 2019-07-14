package com.cristian.shouldemo.fragment.preference

import android.content.Context
import com.cristian.shouldset.view.ShouldSetCategory

class MyCategory(context: Context): ShouldSetCategory(context) {

    override var backgroundColor: Int? = null
    get() =  android.R.color.holo_green_dark

}