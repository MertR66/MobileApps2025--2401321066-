package com.example.midas.data.local

import androidx.room.TypeConverter;
import com.example.midas.data.model.TransactionType;

class Converters {
    @TypeConverter
    fun  fromTransactionType(value: TransactionType): String{
        return value.name
    }
    @TypeConverter
    fun toTransactionType(value: String): TransactionType{
        return enumValueOf(value);
    }
}