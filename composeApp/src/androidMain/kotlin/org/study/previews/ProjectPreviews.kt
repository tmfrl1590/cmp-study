package org.study.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import data.ExpenseManager
import presentation.ExpenseUiState
import ui.AllExpensesHeader
import ui.ExpenseAmount
import ui.ExpenseScreen
import ui.ExpenseTotalHeader
import ui.ExpensesItem

@Preview(showBackground = true)
@Composable
fun ExpensesTotalHeaderPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        ExpenseTotalHeader(total = 1023.67)
    }
}

@Preview(showBackground = true)
@Composable
fun AllExpensesHeaderPreview() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        AllExpensesHeader()
    }
}

@Preview(showBackground = true)
@Composable
fun ExpensesItemPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        ExpensesItem(expense = ExpenseManager.fakeExpenseList[0], onExpenseClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseScreenPreview() {
    ExpenseScreen(
        uiState = ExpenseUiState(
            expense = ExpenseManager.fakeExpenseList,
            total = 1023.67
        ),
        onExpenseClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ExpenseAmountPreview() {
    ExpenseAmount(
        priceContent = 12.0,
        onPriceChange = {},
        keyboardController = LocalSoftwareKeyboardController.current
    )
}
