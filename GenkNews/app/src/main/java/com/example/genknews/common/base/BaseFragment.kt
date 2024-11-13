package com.example.genknews.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.genknews.common.logger.Logger
import com.google.gson.Gson
import javax.inject.Inject

typealias Inflate<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding>() : Fragment() {
    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    constructor(inflate: Inflate<VB>) : this() {
        this.inflate = inflate
    }

    /* **********************************************************************
     * Variable
     ********************************************************************** */
    @Inject
    lateinit var gson: Gson

    @Inject
    protected lateinit var logger: Logger
    protected val binding: VB get() = _binding!!

    private lateinit var inflate: Inflate<VB>
    private var _binding: VB? = null

    /* **********************************************************************
     * Function - Lifecycle
     ********************************************************************** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /* **********************************************************************
     * Function - Abstract
     ********************************************************************** */
    /**
     * Function for handling view in fragment
     */
    abstract fun initView()

    /**
     * Function for handling UI event observer from view model in fragment
     */
    abstract fun initObserver()
}