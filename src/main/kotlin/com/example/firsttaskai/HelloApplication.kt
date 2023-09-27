package com.example.firsttaskai

import com.example.firsttaskai.models.Drink
import com.example.firsttaskai.models.Neighbour
import java.lang.Exception

fun main() {
    launchAIDrinkTask()
}

fun launchAIDrinkTask() {
    //выберем список соседей
    val neighboursList = getNeighboursList()

    println("Введите количество рассматриваемых соседей: (до 5)")
    val neighboursCount = readln().toInt()

    //Введем изначальные данные и составим из них модель
    val initialModel = insertInitialModel()

    //Обработаем изначальные данные (подправим список соседей)
    val filteredNeighbours = getAndPrintNeighboursFilteredByInitialModel(neighboursList, initialModel, neighboursCount)

    //Выведем и напиток
    println(
        getFavorableDrink(filteredNeighbours, initialModel)
    )
}

private fun getNeighboursList(): List<Neighbour> {
    //Выбор списка соседей из заранее созданных
    return Neighbour.COFFEE_WINNERS_LIST
}

private fun insertInitialModel(): Neighbour {
    println("У испытуемого куплен дом? да/нет")
    val isHouseOwner = when (readln()) {
        "да" -> true
        "нет" -> false
        else -> false
    }

    println("У испытуемого есть дети? да/нет")
    val hasChildren = when (readln()) {
        "да" -> true
        "нет" -> false
        else -> false
    }

    println("Какой номер улицы у испытуемого?")
    val streetNum = readln().toInt()

    println("когда просыпается испытуемый?")
    val wakingHour = readln().toInt()

    println("когда засыпает испытуемый?")
    val sleepingHour = readln().toInt()

    println("каков уровень стресса у испытуемого?")
    val stressLevel = readln().toInt()

    println("Сколько часов работает испытуемый?")
    val workingTime = readln().toInt()

    println("Каков возраст испытуемого?")
    val age = readln().toInt()



    return Neighbour(
        streetNum,
        Drink.UNDEFINED,
        wakingHour,
        sleepingHour,
        stressLevel,
        workingTime,
        isHouseOwner,
        hasChildren,
        age
    )
}

private fun getAndPrintNeighboursFilteredByInitialModel(
    neighboursList: List<Neighbour>,
    initialModel: Neighbour,
    neighboursCount: Int
): List<Neighbour> {
    //Вощьмем первые X ближайших к нам соседей
    val newlist = neighboursList
        .sortedBy {
            initialModel.getAbsoluteGraphDistance(it)
        }
        .take(neighboursCount)
        .also { list ->
            list.forEach {
                println(it.toString() + " Расстояние: ${initialModel.getAbsoluteGraphDistance(it)}")
            }
        }

    return newlist.toList()
}

private fun getFavorableDrink(neighbours: List<Neighbour>, initialModel: Neighbour): Drink {

    //Посчитаем любителей кофе и чая
    val teaCount = neighbours.count { it.drink == Drink.TEA }
    val coffeeCount = neighbours.count { it.drink == Drink.COFFEE }

    //Сравним, если неясно любителей поровну - смотрим на другие параметры
    return when {
        teaCount > coffeeCount -> Drink.TEA
        teaCount < coffeeCount -> Drink.COFFEE
        teaCount == coffeeCount -> getDrinkInTie(neighbours, initialModel)
        else -> throw Exception()
    }
}

private fun getDrinkInTie(neighbours: List<Neighbour>, initialModel: Neighbour): Drink {
    //Посортируем по убыванию по оставшимся критериям и достаем напиток
    return neighbours.sortedWith(
        compareByDescending<Neighbour> { it.getAbsoluteGraphDistance(initialModel) }
            .thenByDescending { it.stressLevel }
            .thenByDescending { it.age }
            .thenByDescending { it.workingTime }
            .thenByDescending { it.wakingHour }
            .thenByDescending { it.sleepingHour }
            .thenByDescending { it.streetNum }
    ).first().drink
}