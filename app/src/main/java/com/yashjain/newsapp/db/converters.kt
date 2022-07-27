package com.yashjain.newsapp.db

import androidx.room.TypeConverter
import com.yashjain.newsapp.POJO.Source


class converters {

@TypeConverter
fun fromSource(source: Source):String{
return source.name
}
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}