package ru.rpuxa.survival.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.rpuxa.survival.R

class StuffFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stuff, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
