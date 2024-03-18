package di

import com.expenseApp.db.Database
import data.ExpenseRepoImpl
import domain.ExpenseRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import presentation.ExpensesViewModel

fun appModule(database: Database) = module {
    single<HttpClient> { HttpClient{ install(ContentNegotiation){json()} } }
    single<ExpenseRepository> { ExpenseRepoImpl(database, get()) }
    factory { ExpensesViewModel(get()) }
}