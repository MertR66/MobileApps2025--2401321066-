package com.example.midas.data.model

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.github.mikephil.charting.components.Description

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val type: TransactionType,
    val categoryId: Int,
    var description: String,
    var dateTimestamp: Long,
    var receiptImagePath: String? = null
)
