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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Puppy

@Composable
fun PuppyDetails(puppy: Puppy) {
    val stateDog by remember { mutableStateOf(puppy) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(puppy.imageId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .clip(shape = CutCornerShape(topStart = 0.dp, topEnd = 0.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(
            modifier = Modifier.requiredHeight(26.dp)
        )
        AdoptButton(
            adopted = stateDog.adopted
        )
        Spacer(
            modifier = Modifier.requiredHeight(26.dp)
        )
        PuppyName(puppy)
        PuppyIntro(puppy)
        PuppyDesc(puppy)
    }
    if (showConfirmDialog) {
        AdoptConfirmDialog(puppy = stateDog)
    }
}

@Composable
fun PuppyName(puppy: Puppy) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = puppy.name,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun PuppyDesc(puppy: Puppy) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = puppy.age + "  •  " + puppy.gender,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun PuppyIntro(puppy: Puppy) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = puppy.desc,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface
        )
    }
}

var showConfirmDialog by mutableStateOf(false)

@Composable
fun AdoptButton(adopted: Boolean) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { showConfirmDialog = true },
            enabled = !adopted
        ) {
            Text(text = if (adopted) "Adopted" else "Take him home")
        }
    }
}

@Composable
fun AdoptConfirmDialog(puppy: Puppy) {
    AlertDialog(
        onDismissRequest = {
            showConfirmDialog = false
        },
        text = {
            Text(
                text = "Do you want to adopt this lovely dog?",
                style = MaterialTheme.typography.body2
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    showConfirmDialog = false
                    puppy.adopted = true
                }
            ) {
                Text(
                    text = "Yes"
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showConfirmDialog = false
                }
            ) {
                Text(
                    text = "No"
                )
            }
        }
    )
}
