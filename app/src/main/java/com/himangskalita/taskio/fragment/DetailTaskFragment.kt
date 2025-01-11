package com.himangskalita.taskio.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.himangskalita.taskio.databinding.FragmentDetailTaskBinding

class DetailTaskFragment : Fragment() {

    private lateinit var binding: FragmentDetailTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailTaskBinding.inflate(inflater, container, false)



        return binding.root
    }
}