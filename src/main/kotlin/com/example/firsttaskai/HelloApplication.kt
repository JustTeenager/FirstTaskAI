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
    val filteredNeighbours = getNeighboursFilteredByInitialModel(neighboursList, initialModel, neighboursCount)

    //Выведем и напиток
    println(
        getFavorableDrink(filteredNeighbours)
    )
}

private fun getNeighboursList(): List<Neighbour> {
    //Выбор списка соседей из заранее созданных
    return Neighbour.COFFEE_WINNERS_LIST
}

private fun insertInitialModel(): Neighbour {
    println("У испытуемого куплен дом? да/нет/не знаю")
    val isHouseOwner = when (readln()) {
        "да" -> true
        "нет" -> false
        else -> null
    }

    println("У испытуемого есть дети? да/нет/не знаю")
    val hasChildren = when (readln()) {
        "да" -> true
        "нет" -> false
        else -> null
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

private fun getNeighboursFilteredByInitialModel(
    neighboursList: List<Neighbour>,
    initialModel: Neighbour,
    neighboursCount: Int
): List<Neighbour> {
    //Вощьмем первые X ближайших к нам соседей
    var newlist = neighboursList
        .sortedBy {
            initialModel.getAbsoluteGraphDistance(it)
        }
        .take(neighboursCount)
        .also { list ->
            list.forEach {
                println(it.toString() + " Расстояние: ${initialModel.getAbsoluteGraphDistance(it)}")
            }
        }

    //Если хоть один сосед с таким же состоянием детей -- фильтруем чтобы остался только он
    // Let проверяет ввели мы null (не важно) или нет, если неважно то не происходит ничего
    initialModel.hasChildren?.let { hasChildren ->
        if (newlist.any { hasChildren == it.hasChildren }) {
            newlist = newlist.filter { it.hasChildren == hasChildren }
        }
    }

    //Если хоть один сосед с таким же состоянием дома -- фильтруем чтобы остался только он
    // Let проверяет ввели мы null (не важно) или нет, если неважно то не происходит ничего
    initialModel.hasChildren?.let { isHouseOwner ->
        if (newlist.any { isHouseOwner == it.isHouseOwner }) {
            newlist = newlist.filter { isHouseOwner == initialModel.isHouseOwner }
        }
    }
    return newlist.toList()
}

private fun getFavorableDrink(neighbours: List<Neighbour>): Drink {

    //Посчитаем любителей кофе и чая
    val teaCount = neighbours.count { it.drink == Drink.TEA }
    val coffeeCount = neighbours.count { it.drink == Drink.COFFEE }

    //Сравним, если неясно любителей поровну - смотрим на другие параметры
    return when {
        teaCount > coffeeCount -> Drink.TEA
        teaCount < coffeeCount -> Drink.COFFEE
        teaCount == coffeeCount -> getDrinkInTie(neighbours)
        else -> throw Exception()
    }
}

private fun getDrinkInTie(neighbours: List<Neighbour>): Drink {
    //Посортируем по убыванию по оставшимся критериям и достаем напиток
    return neighbours.sortedWith(
        compareByDescending<Neighbour> { it.stressLevel }
            .thenByDescending { it.age }
            .thenByDescending { it.workingTime }
            .thenByDescending { it.wakingHour }
            .thenByDescending { it.sleepingHour }
            .thenByDescending { it.streetNum }
    ).first().drink
}