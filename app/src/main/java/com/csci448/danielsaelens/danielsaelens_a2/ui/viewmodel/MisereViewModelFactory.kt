package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MisereViewModelFactory(private val bundle: Bundle?) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        private const val LOG_TAG = "MisereViewModelFactory"
    }
    fun getViewModelClass() = MisereViewModel::class.java
    override  fun <T : ViewModel> create(modelClass: Class<T>): T{
        Log.d(LOG_TAG, "Creating $modelClass ")
        if(modelClass.isAssignableFrom(getViewModelClass())){
            return  modelClass
                .getConstructor()
                .newInstance()

        }
        throw IllegalArgumentException("Unknown Viewmodel: $modelClass")
    }




}