package com.kuldeep.posts.commons


import com.kuldeep.deliverylist.detail.di.DaggerDetailComponent
import com.kuldeep.deliverylist.detail.di.DetailComponent
import com.kuldeep.posts.core.application.CoreApp
import com.kuldeep.posts.list.di.DaggerListComponent
import com.kuldeep.posts.list.di.ListComponent
import javax.inject.Singleton

@Singleton
object PostDH {
    private var listComponent: ListComponent? = null
    private var detailComponent:DetailComponent?=null
    fun listComponent(): ListComponent {
        if (listComponent == null)
            listComponent = DaggerListComponent.builder().coreComponent(CoreApp.coreComponent).build()
        return listComponent as ListComponent
    }

    fun detailComponent():DetailComponent{
        if (detailComponent==null) {
            listComponent()
            detailComponent =DaggerDetailComponent.builder().coreComponent(CoreApp.coreComponent).build()

        }
        return detailComponent as DetailComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }
    fun destroyDetailComponent() {
        detailComponent = null
    }

}