package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.cristian.shouldset.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class ShouldSetScreen : LinearLayout {


    private lateinit var mLayout: LinearLayout
    private var mPreferences: MutableList<ShouldSetView>? = null
    private lateinit var bottomSheetLayout: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private val multipleList = mutableListOf<ShouldSetBottomMultiple>()
    private val singleList = mutableListOf<ShouldSetBottomSingle>()
    private lateinit var mTrimLayout: FrameLayout

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                this.background = resources.getDrawable(value, context.theme)
            }
        }


    fun initView(context: Context) {
        inflate(context, R.layout.shouldset_preference_screen, this)
        mLayout = findViewById(R.id.shouldSetScreenLayout)
        mTrimLayout = findViewById(R.id.trimLayout)
        mTrimLayout.visibility = View.GONE
        bottomSheetLayout = findViewById(R.id.bottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        configureBottomSheetBehavior()
        mTrimLayout.setOnClickListener {
            closeBottomSheetDialog()
        }
    }

    private fun configureBottomSheetBehavior() {
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
                if (p1 < 50.0) {
                    val value = ((p1 * 80) / 0.50).toInt()
                    dimBackgroundFromPercentage(value)
                }
            }

            override fun onStateChanged(p0: View, p1: Int) {
                when (p1) {
                    BottomSheetBehavior.STATE_COLLAPSED -> closeBottomSheetDialog()
                    BottomSheetBehavior.STATE_DRAGGING -> null
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> null
                    BottomSheetBehavior.STATE_EXPANDED -> null
                    BottomSheetBehavior.STATE_SETTLING -> null
                    BottomSheetBehavior.STATE_HIDDEN -> null
                }
            }

        })
    }

    fun build(builder: ShouldSetScreen.() -> Unit) {
        this.builder()
    }

    fun openBottomSheetDialogForMultiple(preference: ShouldSetBottomMultiple) {
        bottomSheetLayout.removeAllViews()

        preference.keyLabelPair?.keys?.forEach {

            val checkbox = ShouldSetCheckBoxPreference(it, context)
            checkbox.backgroundColor = preference.backgroundColor
            checkbox.title = preference.keyLabelPair!![it]
            checkbox.key = it
            checkbox.textColor = preference.textColor
            val divider = ShouldSetDividerLine(context)
            divider.color = preference.dividerColor
            bottomSheetLayout.addView(divider, 0)
            bottomSheetLayout.addView(checkbox, 0)
        }
        mTrimLayout.visibility = View.VISIBLE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun openBottomSheetDialogForSingle(preference: ShouldSetBottomSingle) {
        bottomSheetLayout.removeAllViews()
        bottomSheetLayout.addView(preference.radioButtonGroupPreference, 0)
        mTrimLayout.visibility = View.VISIBLE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    fun closeBottomSheetDialog() {
        mTrimLayout.visibility = View.GONE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun addMultipleBottom(bottomMultiple: ShouldSetBottomMultiple) {
        multipleList.add(bottomMultiple)
        bottomMultiple.setOnSelectedListener {
            openBottomSheetDialogForMultiple(bottomMultiple)
        }
    }

    private fun dimBackgroundFromPercentage(percentage: Int) {
        mTrimLayout.animate().alpha((0.5F * percentage) / 100).duration = 0
    }

    fun addSingleBottom(single: ShouldSetBottomSingle) {
        singleList.add(single)
        single.setOnSelectedListener {
            openBottomSheetDialogForSingle(single)
        }
    }

    fun categoryTitle(action: (ShouldSetCategory.() -> Unit)) {
        val category = ShouldSetCategory(context)
        category.action()
        mLayout.addView(category)
    }

    fun descriptor(action: (ShouldSetDescriptor.() -> Unit)) {
        val descriptor = ShouldSetDescriptor(context)
        descriptor.action()
        mLayout.addView(descriptor)
    }

    fun checkBoxPreference(key: String, action: (ShouldSetCheckBoxPreference.() -> Unit)) {
        val checkbox = ShouldSetCheckBoxPreference(key, context)
        checkbox.action()
        mLayout.addView(checkbox)
    }

    fun bottomMultiple(action: (ShouldSetBottomMultiple.() -> Unit)) {
        val multiple = ShouldSetBottomMultiple(context)
        multiple.action()
        mLayout.addView(multiple)
        addMultipleBottom(multiple)

    }

    fun bottomSingle(key: String, defaultValueKey: String, action: (ShouldSetBottomSingle.() -> Unit)) {
        val single = ShouldSetBottomSingle(key, defaultValueKey, context)
        single.action()
        mLayout.addView(single)
        addSingleBottom(single)
    }

    //TODO: Add generic for support boolean values
    fun radioGroup(key: String, defaultValueKey: String, action: (ShouldSetRadioGroupPreference.() -> Unit)) {
        val single = ShouldSetRadioGroupPreference(key, defaultValueKey, context)
        single.action()
        mLayout.addView(single)
    }

    fun dividerLine(action: (ShouldSetDividerLine.() -> Unit)) {
        val divider = ShouldSetDividerLine(context)
        divider.action()
        mLayout.addView(divider)
    }

    fun dividerSpace(action: (ShouldSetDividerSpace.() -> Unit)) {
        val divider = ShouldSetDividerSpace(context)
        divider.action()
        mLayout.addView(divider)
    }

    fun switchPreference(key: String, action: (ShouldSetSwitchPreference.() -> Unit)) {
        val switch = ShouldSetSwitchPreference(key, context)
        switch.action()
        mLayout.addView(switch)
    }
}
