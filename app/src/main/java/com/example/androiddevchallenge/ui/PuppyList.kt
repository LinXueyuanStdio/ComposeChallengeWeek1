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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.PuppyRepo

@Composable
fun PuppyList(modifier: Modifier = Modifier, onItemClick: (id: Int) -> Unit) {
    val puppies = remember { PuppyRepo.getPuppies() }

    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 0.dp
        )
    ) {
        items(puppies) { puppy ->
            PuppyItem(puppy, onItemClick)
            Divider(startIndent = 0.dp)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PuppyItem(
    puppy: Puppy,
    onItemClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier
            .clickable { onItemClick(puppy.id) }
            .padding(vertical = 8.dp),
        icon = {
            Image(
                painter = painterResource(puppy.imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(
                        shape = MaterialTheme.shapes.small.copy(
                            CornerSize(30.dp)
                        )
                    ),
                contentScale = ContentScale.Crop
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = puppy.name, style = MaterialTheme.typography.h6)
                Text(text = puppy.age)
            }
        },
        secondaryText = {
            PuppyMetadata(puppy)
        }
    )
}

@Composable
private fun PuppyMetadata(
    puppy: Puppy,
    modifier: Modifier = Modifier
) {
    val divider = "  •  "
    val tagDivider = "  "
    val text = buildAnnotatedString {
        append(puppy.gender)
        append(divider)
        val tagStyle = MaterialTheme.typography.overline.toSpanStyle().copy(
            background = MaterialTheme.colors.primary.copy(alpha = 0.1f)
        )
        puppy.tags.forEachIndexed { index, tag ->
            if (index != 0) {
                append(tagDivider)
            }
            withStyle(tagStyle) {
                append(" $tag ")
            }
        }
    }
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = modifier
        )
    }
}
