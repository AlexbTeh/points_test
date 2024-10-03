package com.him.eurohim.data.utils

interface Mapper<R,E>{
    fun mapFromApiResponse(type:R):E
}

