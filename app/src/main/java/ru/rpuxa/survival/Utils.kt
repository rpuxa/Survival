package ru.rpuxa.survival

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

val random = Random()



fun <T> MutableLiveData(value: T) = MutableLiveData<T>().apply {
    setValue(value)
}

val <T> LiveData<T>.nnValue: T
    inline get() = value!!

var <T> MutableLiveData<T>.nnValue: T
    inline get() = value!!
    set(value) {
        if (Looper.myLooper() == null) {
            postValue(value)
        } else {
            setValue(value)
        }
    }

suspend fun <T> ui(
    block: suspend CoroutineScope.() -> T
) = withContext(Dispatchers.Main, block)