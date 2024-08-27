package com.montfel.presentation.upsertnote.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldComponent(
    text: String,
    modifier: Modifier = Modifier,
    label: String,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    hasError: Boolean = false,
    errorMessage: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledTextColor = Color.Black,
                disabledBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = singleLine,
            trailingIcon = trailingIcon,
            enabled = enabled,
            shape = RoundedCornerShape(12.dp),
            modifier = modifier.height(60.dp),
        )

        Text(
            text = if (hasError) errorMessage else "",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Red
        )
    }
}
