package com.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.api.repositary

class mainviewmodelfactory(private val repository: repositary) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mainviewmodel(repository) as T
    }

}