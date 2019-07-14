package com.cristian.shouldset.view

import com.cristian.shouldset.manager.ShouldManager
import io.reactivex.disposables.CompositeDisposable

interface ShouldSetPreference {

    val compositeDisposable: CompositeDisposable

    val shouldPreferenceManager: ShouldManager

    var key: String

}