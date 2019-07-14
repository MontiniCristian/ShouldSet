package com.cristian.shouldset.view

open class UnsupportedPreferenceTypeException : Exception() {

    override val message: String?
        get() = "You are probably declaring an list of unsupported preference type, please use a list of strings"
}

open class NullPreferenceKeyException(val preference: Any?) : Exception() {
    override val message: String?
        get() = "A preference should never be configured with a null key"
}

open class NullDefaultValueException(val preference: Any?) : Exception() {
    override val message: String?
        get() = "This preference needs a defaultValue"
}