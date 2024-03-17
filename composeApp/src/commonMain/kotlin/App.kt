import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.TitleTopBarTypes
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import navigation.Navigation
import org.koin.compose.KoinContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    PreComposeApp{
        KoinContext {
            val colors = getColorsTheme()

            AppTheme {
                val navigator = rememberNavigator()
                val titleTopBar = getTitleTopAppBar(navigator)
                val isEditOnAddExpenses = titleTopBar != TitleTopBarTypes.DASHBOARD.value

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = titleTopBar,
                                    fontSize = 24.sp,
                                    color = colors.textColor
                                )
                            },
                            navigationIcon = {
                                if(isEditOnAddExpenses){
                                    IconButton(
                                        onClick = {
                                            navigator.popBackStack()
                                        }
                                    ){
                                        Icon(
                                            modifier = Modifier.padding(start = 16.dp),
                                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                            tint = colors.textColor,
                                            contentDescription = "Back"
                                        )
                                    }
                                }else {
                                    Icon(
                                        modifier = Modifier.padding(start = 16.dp),
                                        imageVector = Icons.Default.Apps,
                                        tint = colors.textColor,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        if(!isEditOnAddExpenses){
                            FloatingActionButton(
                                modifier = Modifier.padding(8.dp),
                                onClick = {
                                    navigator.navigate("/addExpenses")
                                },
                                shape = RoundedCornerShape(50),
                                containerColor = colors.addIconColor,
                                contentColor = Color.White
                            ){
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    tint = Color.White,
                                    contentDescription = "Add"
                                )
                            }

                        }
                    }
                ){
                    Navigation(
                        navigator = navigator,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }

    }
}

@Composable
fun getTitleTopAppBar(navigator: Navigator): String {
    var titleTopBar = TitleTopBarTypes.DASHBOARD
    val isOnAddExpenses = navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if(isOnAddExpenses){
        titleTopBar = TitleTopBarTypes.ADD
    }

    val isOnEditExpenses = navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpenses?.let {
        titleTopBar = TitleTopBarTypes.EDIT
    }
    return titleTopBar.value
}