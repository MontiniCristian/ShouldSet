package com.cristian.shouldset

import android.content.Context
import android.view.View
import com.cristian.shouldset.view.*

class PreferenceConfigurator(val context: Context) {

    private var shouldSetScreen: ShouldSetScreen? = null

    constructor(context: Context, shouldSetScreen: ShouldSetScreen) : this(context) {
        this.shouldSetScreen = shouldSetScreen
    }

    fun categoryTitle(action: (ShouldSetCategory.() -> Unit)): View {
        val category = ShouldSetCategory(context)
        category.action()
        shouldSetScreen?.addItem(category)
        return category
    }

    fun descriptor(action: (ShouldSetDescriptor.() -> Unit)): ShouldSetDescriptor {
        val descriptor = ShouldSetDescriptor(context)
        descriptor.action()
        shouldSetScreen?.addItem(descriptor)
        return descriptor
    }

    fun checkBoxPreference(key: String, action: (ShouldSetCheckBoxPreference.() -> Unit)): ShouldSetCheckBoxPreference {
        val checkbox = ShouldSetCheckBoxPreference(key, context)
        checkbox.action()
        shouldSetScreen?.addItem(checkbox)
        return checkbox
    }

    fun bottomRadioGroupPreference(action: (ShouldSetBottomCheckBoxGroupPreference.() -> Unit)): ShouldSetBottomCheckBoxGroupPreference{
        val bottomRadioGroup = ShouldSetBottomCheckBoxGroupPreference(context)
        bottomRadioGroup.action()
        shouldSetScreen?.addItem(bottomRadioGroup)
        return bottomRadioGroup

    }

    fun bottomCheckBoxGroupPreference(key: String, defaultValueKey: String, action: (ShouldSetBottomRadioGroupPreference.() -> Unit)): ShouldSetBottomRadioGroupPreference {
        val bottomCheckBoxGroup = ShouldSetBottomRadioGroupPreference(key, defaultValueKey, context)
        bottomCheckBoxGroup.action()
        shouldSetScreen?.addItem(bottomCheckBoxGroup)
        return bottomCheckBoxGroup
    }

    fun radioGroupPreference(key: String, defaultValueKey: String, action: (ShouldSetRadioGroupPreference.() -> Unit)): ShouldSetRadioGroupPreference {
        val radioGroup = ShouldSetRadioGroupPreference(key, defaultValueKey, context)
        radioGroup.action()
        shouldSetScreen?.addItem(radioGroup)
        return radioGroup
    }

    fun switchPreference(key: String, action: (ShouldSetSwitchPreference.() -> Unit)): ShouldSetSwitchPreference {
        val switch = ShouldSetSwitchPreference(key, context)
        switch.action()
        shouldSetScreen?.addItem(switch)
        return switch
    }

    fun dividerLine(action: (ShouldSetDividerLine.() -> Unit)): ShouldSetDividerLine {
        val divider = ShouldSetDividerLine(context)
        divider.action()
        shouldSetScreen?.addItem(divider)
        return divider
    }

    fun dividerSpace(action: (ShouldSetDividerSpace.() -> Unit)): ShouldSetDividerSpace {
        val divider = ShouldSetDividerSpace(context)
        divider.action()
        shouldSetScreen?.addItem(divider)
        return divider
    }
}