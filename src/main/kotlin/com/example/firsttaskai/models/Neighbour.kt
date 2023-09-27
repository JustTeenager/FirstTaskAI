package com.example.firsttaskai.models

import kotlin.math.pow
import kotlin.math.sqrt

//Класс соседа
data class Neighbour(
    val streetNum: Int,
    val drink: Drink,
    val wakingHour: Int,
    val sleepingHour: Int,
    val stressLevel: Int,
    val workingTime: Int,
    val isHouseOwner: Boolean,
    val hasChildren: Boolean,
    val age: Int
) {

    fun getAbsoluteGraphDistance(otherNeighbour: Neighbour): Double {
        val isHouseOwnerValue = when (isHouseOwner) {
            true -> 1
            false -> 0
        }

        val otherIsHouseOwnerValue = when (otherNeighbour.isHouseOwner) {
            true -> 1
            false -> 0
        }

        val isHasChildrenValue = when (isHouseOwner) {
            true -> 1
            false -> 0
        }

        val otherIsHasChildrenValue = when (otherNeighbour.isHouseOwner) {
            true -> 1
            false -> 0
        }

        return sqrt(
            (streetNum - otherNeighbour.streetNum).toDouble().pow(2) +
                    (wakingHour - otherNeighbour.wakingHour).toDouble().pow(2) +
                    (sleepingHour - otherNeighbour.sleepingHour).toDouble().pow(2) +
                    (stressLevel - otherNeighbour.stressLevel).toDouble().pow(2) +
                    (workingTime - otherNeighbour.workingTime).toDouble().pow(2) +
                    (age - otherNeighbour.age).toDouble().pow(2) +
                    (isHouseOwnerValue - otherIsHouseOwnerValue).toDouble().pow(2) +
                    (isHasChildrenValue - otherIsHasChildrenValue).toDouble().pow(2)

        )
    }

    companion object {
        val TEA_WINNERS_LIST = listOf(
            Neighbour(
                1,
                Drink.TEA,
                9,
                21,
                10,
                8,
                true,
                true,
                30
            ),
            Neighbour(
                2,
                Drink.TEA,
                10,
                22,
                20,
                8,
                false,
                true,
                35
            ),
            Neighbour(
                3,
                Drink.TEA,
                6,
                9,
                50,
                4,
                true,
                false,
                25
            ),
            Neighbour(
                4,
                Drink.COFFEE,
                16,
                4,
                5,
                4,
                false,
                false,
                20
            ),
            Neighbour(
                5,
                Drink.COFFEE,
                12,
                23,
                90,
                8,
                true,
                true,
                25
            ),
        )

        val COFFEE_WINNERS_LIST = listOf(
            Neighbour(
                1,
                Drink.COFFEE,
                9,
                21,
                10,
                8,
                true,
                true,
                30
            ),
            Neighbour(
                2,
                Drink.COFFEE,
                10,
                22,
                20,
                8,
                false,
                true,
                35
            ),
            Neighbour(
                3,
                Drink.COFFEE,
                6,
                9,
                50,
                4,
                true,
                false,
                25
            ),
            Neighbour(
                4,
                Drink.TEA,
                16,
                4,
                5,
                4,
                false,
                false,
                20
            ),
            Neighbour(
                5,
                Drink.TEA,
                12,
                23,
                90,
                8,
                true,
                true,
                25
            ),
        )
    }
}