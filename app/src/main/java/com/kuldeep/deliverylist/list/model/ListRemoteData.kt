package com.kuldeep.posts.list.model

import androidx.paging.PagedList
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import com.kuldeep.posts.commons.data.remote.PostService
import io.reactivex.Single

class ListRemoteData(private val postService: PostService) : ListDataContract.Remote {

  //  override fun getUsers() = postService.getUsers()

//    override fun getPosts() = postService.getPosts()

    override fun getDeliveries(lint:Int,limit:Int): Single<List<DeliveriesData>> = postService.getDeliveries(lint,limit)

}