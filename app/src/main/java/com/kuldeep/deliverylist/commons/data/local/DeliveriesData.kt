package com.kuldeep.deliverylist.commons.data.local

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [(Index("id"))])
data class DeliveriesData(@SerializedName("id") @PrimaryKey val id: Int,
                          @SerializedName("description") val description: String,
                          @SerializedName("imageUrl") val imageUrl: String,
                          @SerializedName("location")
                          @Embedded
                          val location: LocationData)



