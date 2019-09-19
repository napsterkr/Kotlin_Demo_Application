package com.kuldeep.posts.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kuldeep.core.networking.Outcome
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import com.kuldeep.posts.commons.PostDH
import com.kuldeep.posts.core.extensions.toLiveData
import com.kuldeep.posts.list.model.ListDataContract
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repo: ListDataContract.Repository,
                                        private val compositeDisposable: CompositeDisposable) : ViewModel() {

    val postsOutcome: LiveData<Outcome<List<DeliveriesData>>> by lazy {
        //Convert publish subject to livedata
        repo.postFetchOutcome.toLiveData(compositeDisposable)

    }


    fun getPosts() {
        if (postsOutcome.value == null)
            repo.fetchPosts(0)
    }

    fun refreshPosts() {
        repo.refreshPosts()
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
        PostDH.destroyListComponent()
    }
}