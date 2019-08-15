package ru.rpuxa.survival.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rpuxa.survival.view.App
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Inject
    internal lateinit var map: MutableMap<Class<out ViewModel>, Provider<ViewModel>>

    init {
        App.component.inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        map[modelClass]?.get() as? T ?: error("ViewModel not found!")

}