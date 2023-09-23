package com.example.firsttaskai.models

//Класс того что мы вводим сами
data class InitialModel(
    val neighboursCount: Int,
    val isHouseOwner: Boolean?,
    val hasChildren: Boolean?
)