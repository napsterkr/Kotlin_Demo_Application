package com.kuldeep.deliverylist.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kuldeep.posts.list.model.ListDataContract

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val repository: ListDataContract.Local): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(repository) as T
    }

}