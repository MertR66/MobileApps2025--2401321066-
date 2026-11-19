package com.example.midas.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.midas.data.MidasRepository
import com.example.midas.data.local.MidasDatabase
import com.example.midas.data.model.Category
import com.example.midas.data.model.Goal
import com.example.midas.data.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MidasViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MidasRepository;
    val allTransaction: LiveData<List<Transaction>>;
    val allGoals: LiveData<List<Goal>>

    init{
        val midasDao = MidasDatabase.getDatabase(application).midasDao();
        repository = MidasRepository(midasDao);
        allTransaction = repository.allTransactions.asLiveData();
        allGoals = repository.allGoals.asLiveData();
    }
    fun insertTransaction(transaction: Transaction) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTransaction(transaction);
    }
    fun deleteTransaction(transaction: Transaction) =viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTransaction(transaction);
    }
    fun insertCategory(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCategory(category);
    }
    fun insertGoal(goal: Goal) =viewModelScope.launch(Dispatchers.IO) {
        repository.insertGoal(goal);
    }
    fun updateGoal(goal: Goal) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateGoal(goal);
    }
    fun getCategoriesByType(isIncome: Boolean):Flow<List<Category>> {
        return repository.getCategoriesByType(isIncome);
    }
}


