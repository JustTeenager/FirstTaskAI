package com.example.firsttaskai.models

//Класс соседа
data class Neighbour(
    val distance: Int,
    val drink: Drink,
    val wakingHour: Int,
    val sleepingHour: Int,
    val stressLevel: Int,
    val workingTime: Int,
    val isHouseOwner: Boolean,
    val hasChildren: Boolean,
    val age: Int
) {
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