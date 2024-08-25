package com.montfel.presentation.addeditnote.components

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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldComponent(
    text: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String = "",
    singleLine: Boolean = true,
    enabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        label?.let {
            Text(
                text = it,
            )
        }

        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                unfocusedBorderColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedPlaceholderColor = White.copy(alpha = 0.2f),
                unfocusedPlaceholderColor = White.copy(alpha = 0.2f),
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                disabledTextColor = White,
                disabledPlaceholderColor = White.copy(alpha = 0.2f),
                disabledBorderColor = Color.Transparent,
            ),
            singleLine = singleLine,
            trailingIcon = trailingIcon,
            placeholder = {
                Text(
                    text = placeholder,
                )
            },
            enabled = enabled,
            shape = RoundedCornerShape(12.dp),
            modifier = modifier.height(60.dp),
        )
    }
}
