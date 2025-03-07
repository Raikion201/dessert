package com.example.dessertclicker.data

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert

data class DessertDataClass(
    // First dessert
    val firstDessertIndex: Int = 0,
    val firstDessertsSold: Int = 0,
    val firstDessertPrice: Int = dessertList[0].price,
    @DrawableRes val firstDessertImageId: Int = dessertList[0].imageId,

    // Second dessert
    val secondDessertIndex: Int = 1, // Use a different dessert
    val secondDessertsSold: Int = 0,
    val secondDessertPrice: Int = dessertList[1].price,
    @DrawableRes val secondDessertImageId: Int = dessertList[1].imageId,

    // Total revenue from all desserts
    val revenue: Int = 0,
    val desserts: List<Dessert>
)