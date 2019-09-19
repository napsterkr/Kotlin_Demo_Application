package com.kuldeep.deliverylist.commons.data.local

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity
data class LocationData(@SerializedName("lat") val lat: String,
                        @SerializedName("lng") val lng: String,
                        @SerializedName("address") val address:String)
