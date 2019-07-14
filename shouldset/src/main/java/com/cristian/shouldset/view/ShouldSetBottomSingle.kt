package com.cristian.shouldset.view


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.cristian.shouldset.R
import com.cristian.shouldset.manager.ShouldManager
import io.reactivex.disposables.Disposable

class ShouldSetBottomSingle : LinearLayout {

    private var mValueSubscription: Disposable? = null

    private var mTitleTextView: TextView? = null
    private var mValueTextView: TextView? = null
    private var mOnClickListener: (() -> Unit)? = null

    private val manager: ShouldManager = ShouldManager

    private var value: String? = null

    private var defaultValueKey: String? = null

    var backgroundColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                radioButtonGroupPreference.backgroundColor = value
                mTitleTextView?.background = resources.getDrawable(value, context.theme)
                this.background = resources.getDrawable(value, context.theme)
            }
        }

    lateinit var radioButtonGroupPreference: ShouldSetRadioGroupPreference

    var title: String? = null
        set(value) {
            mTitleTextView?.text = value
            field = value
        }

    var textColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                mTitleTextView?.setTextColor(resources.getColor(value, context.theme))
                radioButtonGroupPreference.textColor = value
            }

        }

    var textValueColor: Int? = null
        set(value) {
            field = value
            if (value != null) mValueTextView?.setTextColor(resources.getColor(value, context.theme))
        }

    var key: String? = null

    var valueLabelPair: (Map<String, String>)? = null
        set(value) {
            field = value
            radioButtonGroupPreference.valueLabelPair = valueLabelPair
            mValueSubscription?.dispose()
            mValueSubscription = manager.getStringAsBehaviorSubject(
                key ?: throw NullDefaultValueException(this),
                defaultValueKey ?: throw NullDefaultValueException(this)
            ).subscribe {
                mValueTextView?.text = valueLabelPair?.get(it)
            }
        }

    var dividerColor: Int? = null
        set(value) {
            field = value
            radioButtonGroupPreference.dividerColor = value
        }

    constructor(key: String, defaultValueKey: String, context: Context) : super(context) {
        this.key = key
        this.defaultValueKey = defaultValueKey
        onInit()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onInit()
    }


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onInit()
    }


    fun setOnSelectedListener(action: () -> Unit) {
        mOnClickListener = action
    }

    private fun onInit() {
        View.inflate(context, R.layout.shouldset_preference_single, this)
        mTitleTextView = findViewById(R.id.shouldSetSingePreferenceTitle)
        mValueTextView = findViewById(R.id.shouldSetSingePreferenceValue)
        radioButtonGroupPreference = ShouldSetRadioGroupPreference(
            key ?: throw NullPreferenceKeyException(this),
            defaultValueKey,
            context
        )

        setOnClickListener {
            mOnClickListener?.invoke()
        }

        elevation = 8.0F
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}