package com.inmy.products.data.room.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "products_table")
class Product (@PrimaryKey(autoGenerate = true) val id: Int = 0,
               @ColumnInfo(name = "product_image") val P_image: String,
               @ColumnInfo(name = "product_name") val P_name: String,
               @ColumnInfo(name = "product_detail") val P_detail: String,
               @ColumnInfo(name = "product_category") val P_category: String?,
               @ColumnInfo(name = "product_subCategory") val P_subCategory: String?,
               @ColumnInfo(name = "product_price") val P_price: Int?,
               @ColumnInfo(name = "product_length") val P_length: Int?,
               @ColumnInfo(name = "product_width") val P_width: Int?,
               @ColumnInfo(name = "product_height") val P_height: Int?,
               @ColumnInfo(name = "product_images") val P_images: String?) : Parcelable