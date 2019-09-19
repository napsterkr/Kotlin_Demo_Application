package com.kuldeep.posts.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kuldeep.core.networking.Outcome
import com.kuldeep.deliverylist.R
import com.kuldeep.deliverylist.commons.data.local.DeliveriesData
import com.kuldeep.deliverylist.detail.DetailActivity
import com.kuldeep.posts.commons.PostDH
import com.kuldeep.posts.core.application.BaseActivity
import com.kuldeep.posts.list.viewmodel.ListViewModel
import com.kuldeep.posts.list.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_list.*
import java.io.IOException
import javax.inject.Inject

class ListActivity : BaseActivity(), PostListAdapter.Interaction {

    private val component by lazy { PostDH.listComponent() }

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory

    @Inject
    lateinit var adapter: PostListAdapter

    @Inject
    lateinit var viewModel: ListViewModel /*by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }*/

    private val context: Context by lazy { this }

    private val TAG = "ListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        component.inject(this)

        adapter.interaction = this
        rvPosts.adapter = adapter
        srlPosts.setOnRefreshListener { viewModel.refreshPosts() }

        viewModel.getPosts()
        initiateDataListener()
    }

    private fun initiateDataListener() {
        //Observe the outcome and update state of the screen  accordingly
        viewModel.postsOutcome.observe(this, Observer<Outcome<List<DeliveriesData>>> { outcome ->
            Log.d(TAG, "initiateDataListener: $outcome")
            when (outcome) {

                is Outcome.Progress -> srlPosts.isRefreshing = outcome.loading

                is Outcome.Success -> {
                    Log.d(TAG, "initiateDataListener: Successfully loaded data")
                    srlPosts.isRefreshing=false
                    adapter.swapData(outcome.data)
                }

                is Outcome.Failure -> {
                    srlPosts.isRefreshing=false
                    if (outcome.e is IOException)
                        Toast.makeText(
                                context,
                                R.string.need_internet_posts,
                                Toast.LENGTH_LONG
                        ).show()
                    else
                        Toast.makeText(
                                context,
                                R.string.failed_post_try_again,
                                Toast.LENGTH_LONG
                        ).show()
                }

            }
        })
    }

    override fun postClicked(
            post: DeliveriesData,
            tvTitle: TextView,
            tvBody: TextView,
            ivAvatar: ImageView
    ) {
        var intent=Intent(context,DetailActivity::class.java)
        intent.putExtra("imageUrl",post.imageUrl)
        intent.putExtra("description",post.description)
        intent.putExtra("lat",post.location.lat)
        intent.putExtra("lng",post.location.lng)
        intent.putExtra("address",post.location.address)
        startActivity(intent)
    }

}
