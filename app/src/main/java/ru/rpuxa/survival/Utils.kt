package ru.rpuxa.survival

import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.rpuxa.survival.viewmodel.factories.ViewModelFactory
import java.util.*

val random = Random()


@Suppress("FunctionName")
fun <T> MutableLiveData(value: T) = MutableLiveData<T>().apply {
    setValue(value)
}

@Suppress("UNCHECKED_CAST")
fun <T> nullLiveData() = NullLiveData
        as LiveData<T?>

object NullLiveData : LiveData<Nothing?>() {
    init {
        postValue(null)
    }
}

var <T> MutableLiveData<T>.nnValue: T
    @Deprecated("Use value!!")
    inline get() = value!!
    set(value) {
        if (Looper.myLooper() == null) {
            postValue(value)
        } else {
            setValue(value)
        }
    }

inline fun <T> MutableLiveData<T>.update(block: T.() -> Unit = {}) {
    val v = value
    v!!.block()
    value = v
}

suspend inline fun <T> ui(
    noinline block: suspend CoroutineScope.() -> T
) = withContext(Dispatchers.Main, block)


inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline block: (T) -> Unit) =
    observe(owner, Observer { block(it) })

inline val Fragment.lazyNavController get() = lazy { findNavController() }

@Suppress("UNCHECKED_CAST")
fun <T> getEqualsDiff() = EqualsDiff as DiffUtil.ItemCallback<T>

private object EqualsDiff : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Any, newItem: Any) =
        areItemsTheSame(oldItem, newItem)
}

fun ViewGroup.inflate(@LayoutRes res: Int): View {
    val inflater = LayoutInflater.from(context)
    return inflater.inflate(res, this, false)
}

inline fun <reified VM : ViewModel> ComponentActivity.viewModel() = viewModels<VM>(::ViewModelFactory)
inline fun <reified VM : ViewModel> Fragment.viewModel() = activityViewModels<VM>(::ViewModelFactory)

