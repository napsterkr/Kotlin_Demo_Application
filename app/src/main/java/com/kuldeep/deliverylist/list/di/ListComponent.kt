package com.kuldeep.posts.list.di

import androidx.room.Room
import android.content.Context
import com.kuldeep.deliveries.core.constants.Constants
import com.kuldeep.posts.commons.data.local.DeliveriesDb
import com.kuldeep.posts.commons.data.remote.PostService
import com.kuldeep.posts.core.di.CoreComponent
import com.kuldeep.posts.core.networking.Scheduler
import com.kuldeep.posts.list.ListActivity
import com.kuldeep.posts.list.PostListAdapter
import com.kuldeep.posts.list.model.ListDataContract
import com.kuldeep.posts.list.model.ListLocalData
import com.kuldeep.posts.list.model.ListRemoteData
import com.kuldeep.posts.list.model.ListRepository
import com.kuldeep.posts.list.viewmodel.ListViewModel
import com.kuldeep.posts.list.viewmodel.ListViewModelFactory
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@ListScope
@Component(dependencies = [CoreComponent::class], modules = [ListModule::class])
interface ListComponent {

    //Expose to dependent components
    fun postDb(): DeliveriesDb

    fun postService(): PostService
    fun picasso(): Picasso
    fun scheduler(): Scheduler

    fun inject(listActivity: ListActivity)
}

@Module
@ListScope
class ListModule {

    /*Adapter*/
    @Provides
    @ListScope
    fun adapter(picasso: Picasso): PostListAdapter = PostListAdapter(picasso)

    /*ViewModelFactory*/
    @Provides
    @ListScope
    fun listViewModelFactory(repository: ListDataContract.Repository, compositeDisposable: CompositeDisposable): ListViewModelFactory = ListViewModelFactory(repository,compositeDisposable)

    /*ViewModel*/
    @Provides
    @ListScope
    fun listViewModle(repo: ListDataContract.Repository,
                      compositeDisposable: CompositeDisposable): ListViewModel = ListViewModel(repo,compositeDisposable)
    /*Repository*/
    @Provides
    @ListScope
    fun listRepo(local: ListDataContract.Local, remote: ListDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): ListDataContract.Repository = ListRepository(local, remote, scheduler, compositeDisposable)

    @Provides
    @ListScope
    fun remoteData(postService: PostService): ListDataContract.Remote = ListRemoteData(postService)

    @Provides
    @ListScope
    fun localData(postDb: DeliveriesDb, scheduler: Scheduler): ListDataContract.Local = ListLocalData(postDb, scheduler)

    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    /*Parent providers to dependents*/
    @Provides
    @ListScope
    fun postDb(context: Context): DeliveriesDb =
        Room.databaseBuilder(context, DeliveriesDb::class.java, Constants.Posts.DB_NAME).build()

    @Provides
    @ListScope
    fun postService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}