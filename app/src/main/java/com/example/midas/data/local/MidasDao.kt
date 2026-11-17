package com.example.midas.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.midas.data.model.Category
import com.example.midas.data.model.Goal
import com.example.midas.data.model.Transaction
import kotlinx.coroutines.flow.Flow
@Dao
interface MidasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)
    @Update
    suspend fun updateTransaction(transaction: androidx.room.Transaction)
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
    @Query("SELECT * FROM transactions ORDER BY dateTimestamp DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)
    @Query("SELECT * FROM categories WHERE isIncome = :isIncome")
    fun getCategoryByType(isIncome: Boolean): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal)
    @Update
    suspend fun updateGoal(goal: Goal)
    @Query("SELECT * FROM goals")
    fun getAllGoals(): Flow<List<Goal>>
}