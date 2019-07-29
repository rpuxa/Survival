package ru.rpuxa.survival

import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

val random = Random()



fun <T> MutableLiveData(value: T) = MutableLiveData<T>().apply {
    setValue(value)
}

var <T> MutableLiveData<T>.nnValue: T
    inline get() = value!!
    set(value) {
        if (Looper.myLooper() == null) {
            postValue(value)
        } else {
            setValue(value)
        }
    }

fun <T> MutableLiveData<T>.update() {
    value = value
}

suspend fun <T> ui(
    block: suspend CoroutineScope.() -> T
) = withContext(Dispatchers.Main, block)


inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline block: (T) -> Unit) =
    observe(owner, Observer { block(it) })

inline val Fragment.lazyNavController get() = lazy { findNavController() }

