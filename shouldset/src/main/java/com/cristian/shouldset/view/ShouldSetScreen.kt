package com.cristian.shouldset.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.cristian.shouldset.PreferenceConfigurator
import com.cristian.shouldset.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class ShouldSetScreen : LinearLayout {

    private lateinit var mLayout: LinearLayout
    private lateinit var bottomSheetLayout: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var mTrimLayout: FrameLayout
    private lateinit var mPreferenceConfigurator: PreferenceConfigurator

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

    private fun initView(context: Context) {
        inflate(context, R.layout.shouldset_preference_screen, this)
        mLayout = findViewById(R.id.shouldSetScreenLayout)
        mTrimLayout = findViewById(R.id.trimLayout)
        mTrimLayout.visibility = View.GONE
        bottomSheetLayout = findViewById(R.id.bottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        mPreferenceConfigurator = PreferenceConfigurator(context, this)
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

    /**
     * This method use the kotlin DSL to build the ShouldSetScreen content.
     *
     * Usage example:
     *```kotlin
     *  myShouldSetScreen.configure {
     *    checkBoxPreference("isChecked") {
     *      title = "Check me"
     *    }
     *  }
     * ```
     */
    fun configure(resetView: Boolean = false, builder: PreferenceConfigurator.() -> View) {
        if (resetView) mLayout.removeAllViews()
        val items = mPreferenceConfigurator.builder()
        when(items) {
            is ShouldSetBottomCheckBoxGroupPreference -> addBottomCheckBoxGroupPreference(items)
            is ShouldSetBottomRadioGroupPreference -> addBottomRadioGroupPreference(items)
        }
    }

    fun addItem(view: View) {
        mLayout.addView(view)
        when(view) {
            is ShouldSetBottomCheckBoxGroupPreference -> addBottomCheckBoxGroupPreference(view)
            is ShouldSetBottomRadioGroupPreference -> addBottomRadioGroupPreference(view)
        }
    }

    fun addItemList(viewListClosure: () -> List<View>) {
        viewListClosure.invoke().forEach {
            addItem(it)
            when(it) {
                is ShouldSetBottomCheckBoxGroupPreference -> addBottomCheckBoxGroupPreference(it)
                is ShouldSetBottomRadioGroupPreference -> addBottomRadioGroupPreference(it)
            }
        }

    }

    fun addItemList(itemList: List<View>) {
        itemList.forEach {
            addItem(it)
        }
    }

    private fun openBottomSheetDialogForCheckBoxGroup(preference: ShouldSetBottomCheckBoxGroupPreference) {
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

    private fun openBottomSheetDialogForRadioGroup(preference: ShouldSetBottomRadioGroupPreference) {
        bottomSheetLayout.removeAllViews()
        bottomSheetLayout.addView(preference.radioButtonGroupPreference, 0)
        mTrimLayout.visibility = View.VISIBLE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    private fun closeBottomSheetDialog() {
        mTrimLayout.visibility = View.GONE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun addBottomRadioGroupPreference(preference: ShouldSetBottomRadioGroupPreference) {
        preference.setOnSelectedListener {
            openBottomSheetDialogForRadioGroup(preference)
        }
    }

    private fun dimBackgroundFromPercentage(percentage: Int) {
        mTrimLayout.animate().alpha((0.5F * percentage) / 100).duration = 0
    }

    private fun addBottomCheckBoxGroupPreference(preference: ShouldSetBottomCheckBoxGroupPreference) {
        preference.setOnSelectedListener {
            openBottomSheetDialogForCheckBoxGroup(preference)
        }
    }
}
