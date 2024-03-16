package navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import data.ExpenseManager
import data.ExpenseRepoImpl
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import presentation.ExpenseViewModel
import ui.ExpenseScreen

@Composable
fun Navigation(
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    val colors = getColorsTheme()
    val viewModel = viewModel(modelClass = ExpenseViewModel::class){
        ExpenseViewModel(ExpenseRepoImpl(ExpenseManager))
    }

    NavHost(
        modifier = modifier.background(colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ){
        scene(route = "/home"){
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpenseScreen(
                uiState = uiState,
            ){ expense ->
                navigator.navigate("/addExpenses/${expense.id}")
            }
        }

        scene(route = "/addExpenses/{id}"){ backStackEntry ->
            val idFromPath = backStackEntry.path<Long>("id")
            val expenseToEditOrAdd = idFromPath?.let { id -> viewModel.getExpenseWithId(id) }
        }
    }

}