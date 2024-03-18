import androidx.compose.ui.window.ComposeUIViewController
import com.expenseApp.db.Database
import data.DatabaseDriverFactory
import di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin(){
    startKoin {
        modules(appModule(Database.invoke(DatabaseDriverFactory().createDriver())))
    }.koin
}