package com.example.newsfetcher.base

import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

// Программно переопределять шрифт вьюхи
fun TextView.setTextAppearanceCompat(@StyleRes styleRes: Int) {
    TextViewCompat.setTextAppearance(this, styleRes)
}