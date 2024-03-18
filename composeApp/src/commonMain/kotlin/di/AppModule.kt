package di

import com.expenseApp.db.Database
import data.ExpenseRepoImpl
import domain.ExpenseRepository
import org.koin.dsl.module
import presentation.ExpenseViewModel

fun appModule(database: Database) = module {
    single<ExpenseRepository> { ExpenseRepoImpl(database) }
    factory { ExpenseViewModel(get()) }
}