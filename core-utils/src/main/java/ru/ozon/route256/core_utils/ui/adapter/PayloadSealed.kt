package ru.ozon.route256.core_utils.ui.adapter

interface PayloadSealed

fun PayloadSealed.getKey(): String = this::class.java.name