/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.data.PuppyRepo
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.grey

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val selectedPuppyId = remember { mutableStateOf(PuppyRepo.selectedPuppyId) }

                ShowPuppiesList(selectedPuppyId)
            }
        }
    }
}

@Composable
private fun ShowPuppiesList(selectedPuppyId: MutableState<Int>) {
    val puppy = PuppyRepo.getPuppy(selectedPuppyId.value)

    val icon = if (puppy.id < 1) Icons.Rounded.Pets else Icons.Rounded.ArrowBack
    val title = if (puppy.id < 1) stringResource(R.string.app_name) else puppy.name
    val onIconClick: (() -> Unit) = if (puppy.id < 1) ({}) else (
        {
            selectedPuppyId.value = 0
        }
        )

    val actions: @Composable RowScope.() -> Unit = if (puppy.id < 1) {
        {}
    } else {
        {
            var isSelected by remember(calculation = { mutableStateOf(false) })
            val iconColor by animateColorAsState(if (isSelected) Color.White else grey)
            IconButton(onClick = { isSelected = !isSelected }) {
                Icon(Icons.Filled.Favorite, contentDescription = null, tint = iconColor)
            }
        }
    }
    MyApp(
        icon = icon,
        onIconClick = onIconClick,
        title = title,
        actions = actions
    ) {
        if (selectedPuppyId.value < 1) {
            PuppyList { clickedPuppyId ->
                selectedPuppyId.value = clickedPuppyId
            }
        } else {
            PuppyDetails(puppy = puppy)
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        val selectedPuppyId = remember { mutableStateOf(PuppyRepo.selectedPuppyId) }

        ShowPuppiesList(selectedPuppyId)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        val selectedPuppyId = remember { mutableStateOf(PuppyRepo.selectedPuppyId) }

        ShowPuppiesList(selectedPuppyId)
    }
}
