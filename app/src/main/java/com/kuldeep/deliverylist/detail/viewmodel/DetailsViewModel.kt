package com.kuldeep.deliverylist.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import com.kuldeep.posts.list.model.ListDataContract
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val repo: ListDataContract.Local) :ViewModel(){

    val postsOutcome: List<DeliveriesData> by lazy {
        //Convert publish subject to livedata
        repo.getDeliveryById(123)

    }

    fun getDeliveryDataById(id:Int):List<DeliveriesData>{

        return postsOutcome

    }



}