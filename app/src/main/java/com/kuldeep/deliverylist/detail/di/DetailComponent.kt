package com.kuldeep.deliverylist.detail.di

import com.kuldeep.deliverylist.detail.DetailActivity
import com.kuldeep.deliverylist.detail.viewmodel.DetailsViewModel
import com.kuldeep.deliverylist.detail.viewmodel.DetailsViewModelFactory
import com.kuldeep.posts.commons.data.local.DeliveriesDb
import com.kuldeep.posts.core.di.CoreComponent
import com.kuldeep.posts.core.networking.Scheduler
import com.kuldeep.posts.list.di.DetailsScope
import com.kuldeep.posts.list.di.ListScope
import com.kuldeep.posts.list.model.ListDataContract
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Component(dependencies = [CoreComponent::class], modules = [DetailsModule::class])
@DetailsScope
interface DetailComponent {

    //Expose to dependent components
  //  fun postDb(): DeliveriesDb

    fun picasso(): Picasso

    fun inject(detailActivity: DetailActivity)
}


@Module
@DetailsScope
class DetailsModule{

    /*ViewModelFactory*/
    @Provides
    @DetailsScope
    fun detailViewModelFactory(repository: ListDataContract.Local): DetailsViewModelFactory = DetailsViewModelFactory(repository)

    /*ViewModel*/
    @Provides
    @DetailsScope
    fun detailViewModle(repo: ListDataContract.Local,
                      compositeDisposable: CompositeDisposable):DetailsViewModel= DetailsViewModel(repo)



}