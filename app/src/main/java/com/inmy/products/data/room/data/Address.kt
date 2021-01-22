package com.inmy.products.data.room.data

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "address_table")
data class Address(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                   @ColumnInfo(name = "name") val name: String,
                   @ColumnInfo(name = "mobile") val mobile: String,
                   @ColumnInfo(name = "pincode") val pincode: String?,
                   @ColumnInfo(name = "address") val address: String?,
                    @ColumnInfo(name = "city") val city: String?,
                    @ColumnInfo(name = "state") val state: String?) :Parcelable
