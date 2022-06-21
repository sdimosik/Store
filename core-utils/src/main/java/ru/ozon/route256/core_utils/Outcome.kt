package ru.ozon.route256.core_utils

sealed class Outcome<out T> {
    data class Success<T>(val value: T) : Outcome<T>()
    data class Failure(val cause: Throwable) : Outcome<Nothing>()
}

inline fun <T> Outcome(block: () -> T): Outcome<T> =
    try {
        Outcome.Success(block())
    } catch (t: Throwable) {
        Outcome.Failure(t)
    }

fun <T> Outcome<T>.requireValue(): T =
    when (this) {
        is Outcome.Success -> value
        is Outcome.Failure -> throw cause
    }

fun <T> Outcome<T>.requireError(): Throwable =
    when (this) {
        is Outcome.Success -> throw UnsupportedOperationException()
        is Outcome.Failure -> cause
    }