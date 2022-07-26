package ru.ozon.route256.core_utils.ui.adapter

interface BaseDiffModel {
    val id: Long
    fun isIdEqual(other: BaseDiffModel): Boolean = id == other.id
}