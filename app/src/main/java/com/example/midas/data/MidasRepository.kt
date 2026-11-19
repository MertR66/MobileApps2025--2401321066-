package com.example.midas.data

import com.example.midas.data.local.MidasDao
import com.example.midas.data.model.Category
import com.example.midas.data.model.Goal
import com.example.midas.data.model.Transaction
import kotlinx.coroutines.flow.Flow

class MidasRepository(private val midasDao: MidasDao) {
    val allTransactions: Flow<List<Transaction>> = midasDao.getAllTransactions();
    val allGoals: Flow<List<Goal>> = midasDao.getAllGoals();
    fun getCategoriesByType(isIncome: Boolean):Flow<List<Category>>{
        return midasDao.getCategoryByType(isIncome);
    }

    suspend fun insertTransaction(transaction: Transaction){
        midasDao.insertTransaction(transaction)
    }
    suspend fun deleteTransaction(transaction: Transaction){
        midasDao.deleteTransaction(transaction)
    }

    suspend fun insertCategory(category: Category){
        midasDao.insertCategory(category);
    }

    suspend fun insertGoal(goal: Goal){
        midasDao.insertGoal(goal);
    }

    suspend fun updateGoal(goal: Goal){
        midasDao.updateGoal(goal);
    }

}