package com.cristian.shouldemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cristian.shouldemo.R
import com.cristian.shouldset.manager.ShouldManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_fragment_container, FragmentFirst())
            ?.commit()

        ShouldManager.getBooleanAsBehaviorSubject("areGreetingsHappening", true).subscribe {
            print("Updated value $it")
        }

        with(bottomNavigationView as BottomNavigationView) {
            this.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_map -> {
                        // currentFragment = ThoriumMapFragment.getInstance()
                        fragmentManager?.beginTransaction()!!
                            .replace(R.id.main_fragment_container, FragmentFirst())
                            .addToBackStack("mapFragment")
                            .commit()
                        true
                    }

                    R.id.action_settings -> {
                        fragmentManager?.beginTransaction()!!
                            .replace(R.id.main_fragment_container, FragmentSettings())
                            .addToBackStack("mapFragment")
                            .commit()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    fun hideBottomNavigationView() {
        bottomNavigationView.clearAnimation()
        bottomNavigationView.animate().translationY(bottomNavigationView.height.toFloat()).duration = 300
    }

    fun showBottomNavigationView() {
        bottomNavigationView.clearAnimation()
        bottomNavigationView.animate().translationY(0f).duration = 300
    }
}