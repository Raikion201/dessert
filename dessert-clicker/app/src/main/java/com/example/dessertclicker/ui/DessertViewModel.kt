package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.DessertDataClass
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for managing the data and business logic of the DessertClicker app
 */
class DessertSharingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        DessertDataClass(
            revenue = 0,
            firstDessertsSold = 0,
            secondDessertsSold = 0,
            firstDessertImageId = Datasource.dessertList[0].imageId,
            firstDessertPrice = Datasource.dessertList[0].price,
            secondDessertImageId = Datasource.dessertList[1 % Datasource.dessertList.size].imageId,
            secondDessertPrice = Datasource.dessertList[1 % Datasource.dessertList.size].price,
            desserts = Datasource.dessertList
        )
    )
    val uiState: StateFlow<DessertDataClass> = _uiState.asStateFlow()

    /**
     * Update when the first dessert is clicked
     */
    fun onFirstDessertClicked() {
        _uiState.update { currentState ->
            val newDessertsSold = currentState.firstDessertsSold + 1
            val dessertToShow = determineDessertToShow(
                newDessertsSold,
                currentState.desserts
            )

            currentState.copy(
                revenue = currentState.revenue + currentState.firstDessertPrice,
                firstDessertsSold = newDessertsSold,
                firstDessertImageId = dessertToShow.imageId,
                firstDessertPrice = dessertToShow.price
            )
        }
    }

    /**
     * Update when the second dessert is clicked
     */
    fun onSecondDessertClicked() {
        _uiState.update { currentState ->
            val newDessertsSold = currentState.secondDessertsSold + 1
            val dessertToShow = determineDessertToShow(
                newDessertsSold,
                currentState.desserts,
                startIndex = 1
            )

            currentState.copy(
                revenue = currentState.revenue + currentState.secondDessertPrice,
                secondDessertsSold = newDessertsSold,
                secondDessertImageId = dessertToShow.imageId,
                secondDessertPrice = dessertToShow.price
            )
        }
    }

    /**
     * Determine which dessert to show based on the number of desserts sold
     */
    private fun determineDessertToShow(
        dessertsSold: Int,
        desserts: List<Dessert>,
        startIndex: Int = 0
    ): Dessert {
        var dessertToShow = desserts[startIndex % desserts.size]
        for (i in startIndex until desserts.size) {
            val dessert = desserts[i]
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}