package com.him.eurohim.data.utils

interface Mapper<From, To> {
    fun map(from: From): To
}
