package data

import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

private const val BASE_URL = "http://192.168.0.102:8080"

class ExpenseRepoImpl(
    private val expenseManager: ExpenseManager
) : ExpenseRepository {
    override fun getAllExpenses(): List<Expense> {
        return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        expenseManager.addNewExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        expenseManager.editExpense(expense)
    }

    override fun getCategories(): List<ExpenseCategory> {
        return expenseManager.getCategories()
    }

    override suspend fun deleteExpense(id: Long): List<Expense> {
        TODO("Not yet implemented")
    }
}