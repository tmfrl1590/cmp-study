package data

import com.expenseApp.db.Database
import domain.ExpenseRepository
import model.Expense
import model.ExpenseCategory

private const val BASE_URL = "http://192.168.0.102:8080"

class ExpenseRepoImpl(
    private val appDatabase: Database,
) : ExpenseRepository {

    private val queries = appDatabase.appDatabaseQueries

    override fun getAllExpenses(): List<Expense> {
        return queries.selectAll().executeAsList().map {
            Expense(
                id = it.id,
                amount = it.amount,
                category = ExpenseCategory.valueOf(it.categoryName),
                description = it.description
            )
        }
    }

    override fun addExpense(expense: Expense) {
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun editExpense(expense: Expense) {
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                categoryName = expense.category.name,
                description = expense.description
            )
        }
    }

    override fun getCategories(): List<ExpenseCategory> {
        return queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }

    override fun deleteExpense(id: Long): List<Expense> {
        TODO("Not yet implemented")
    }
}