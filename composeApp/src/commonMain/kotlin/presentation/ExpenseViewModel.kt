package presentation

import domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class ExpenseUiState(
    val expense: List<Expense> = emptyList(),
    val total: Double = 0.0
)

class ExpenseViewModel(
    private val repo: ExpenseRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseUiState())
    val uiState = _uiState.asStateFlow()
    private var allExpenses: MutableList<Expense> = mutableListOf()

    init {
        getAllExpenses()
    }

    private fun updateExpenseList(){
        viewModelScope.launch {
            allExpenses = repo.getAllExpenses().toMutableList()
            updateState()
        }
    }


    private fun updateState(){
        _uiState.update { state ->
            state.copy(expense = allExpenses, total = allExpenses.sumOf { it.amount })
        }
    }
    private fun getAllExpenses(){
        repo.getAllExpenses()
        updateExpenseList()
    }

    fun addExpense(expense: Expense){
        repo.addExpense(expense)
        updateExpenseList()
    }

    fun editExpense(expense: Expense){
        repo.editExpense(expense)
        updateExpenseList()
    }

    fun deleteExpense(id: Long){
        repo.deleteExpense(id)
        updateExpenseList()
    }

    fun getExpenseWithId(id: Long): Expense {
        return allExpenses.first { it.id == id }
    }
}