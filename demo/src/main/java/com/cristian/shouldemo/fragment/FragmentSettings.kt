package com.cristian.shouldemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cristian.shouldemo.R

import kotlinx.android.synthetic.main.fragment_settings.*

class FragmentSettings : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        myShouldSetScreen.build {

            backgroundColor = R.color.colorPrimaryDark

            categoryTitle {
                title = "Social Skills"
                textColor = R.color.colorSecondary
            }

            checkBoxPreference("areGreetingsHappening") {
                backgroundColor = R.color.colorPrimary
                textColor = android.R.color.white
                setOnValueChangeListener {
                    if (it) print("Say hello")
                    if (!it) print("I'll be quiet")
                }
                title = "Enable Greetings"
            }

            descriptor {
                backgroundColor = R.color.colorPrimaryDark
                description = "Enabling this option will improve your social skills."
                textColor = R.color.colorSecondary
            }

            bottomMultiple {
                keyLabelPair = hashMapOf(
                    "isSomething" to "Something",
                    "isMagic" to "Magic",
                    "areGreetingsHappening" to "Alternative Greet"
                )
                backgroundColor = R.color.colorPrimary
                textColor = android.R.color.white
                title = "Other Greetings"
                dividerColor = R.color.colorPrimaryDark
            }

            dividerLine {
                color = R.color.colorPrimaryDark
            }

            categoryTitle {
                title = "Items"
                textColor = R.color.colorSecondary
            }

            switchPreference("isSwitched") {
                backgroundColor = R.color.colorPrimary
                textColor = android.R.color.white
                title = "Switch this"
            }

            dividerLine {
                color = R.color.colorPrimaryDark
            }

            bottomSingle("magic_kind", "duperMagic") {
                backgroundColor = R.color.colorPrimary
                textColor = android.R.color.white
                title = "Magic kind"
                dividerColor = R.color.colorPrimaryDark
                textValueColor = R.color.colorSecondary
                valueLabelPair = hashMapOf(
                    "superMagic" to "Super Magic",
                    "duperMagic" to "Duper Magic",
                    "anotherMagic" to "Another Magic",
                    "strangeMagic" to "Strange Magic",
                    "iperMagic" to "Iper Magic"
                )
            }

            descriptor {
                backgroundColor = R.color.colorPrimaryDark
                description = "Select the magic kind you prefer in order to do some magic."
                textColor = R.color.colorSecondary
            }
        }
    }
}