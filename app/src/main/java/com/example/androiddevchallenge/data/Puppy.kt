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
package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.androiddevchallenge.R

@Immutable
data class Puppy(
    val id: Int,
    val name: String,
    val age: String,
    val gender: String,
    val desc: String,
    @DrawableRes val imageId: Int,
    val tags: Set<String>
)

object PuppyRepo {
    fun getPuppies(): List<Puppy> = puppies
    fun getPuppy(id: Int) = puppies.find {
        it.id == id
    } ?: p0
    const val selectedPuppyId = 0
}

private val p0 = Puppy(
    id = 0,
    name = "xx",
    age = "0 year",
    gender = "xx",
    desc = "Error",
    imageId = R.drawable.ic_launcher_background,
    tags = setOf("xx", "yy")
)

private val p1 = Puppy(
    id = 1,
    name = "Bella",
    age = "1 year",
    gender = "male",
    desc = "He has beautiful, jade-green eyes.",
    imageId = R.drawable.puppy_1,
    tags = setOf("Beautiful", "Cute")
)

private val p2 = Puppy(
    id = 2,
    name = "Charlie",
    age = "1 year",
    gender = "male",
    desc = "His fur is silky and smooth.",
    imageId = R.drawable.puppy_2,
    tags = setOf("Beautiful", "Cute")
)

private val p3 = Puppy(
    id = 3,
    name = "Luna",
    age = "6 months",
    gender = "female",
    desc = "She has tiny, hedgehog paws.",
    imageId = R.drawable.puppy_3,
    tags = setOf("Beautiful", "Cute")
)

private val p4 = Puppy(
    id = 4,
    name = "Lucy",
    age = "1 year",
    gender = "male",
    desc = "Wagging his tail is his favourite activity!",
    imageId = R.drawable.puppy_4,
    tags = setOf("Beautiful", "Cute")
)

private val p5 = Puppy(
    id = 5,
    name = "Max",
    age = "2 years",
    gender = "male",
    desc = "He is very playful and a joy to be around.",
    imageId = R.drawable.puppy_5,
    tags = setOf("Beautiful", "Cute")
)

private val p6 = Puppy(
    id = 6,
    name = "Bailey",
    age = "9 months",
    gender = "male",
    desc = "His teeth are as sharp as needles.",
    imageId = R.drawable.puppy_6,
    tags = setOf("Beautiful", "Cute")
)

private val p7 = Puppy(
    id = 7,
    name = "Cooper",
    age = "1 year",
    gender = "male",
    desc = "He is always yelping at the cat.",
    imageId = R.drawable.puppy_7,
    tags = setOf("Beautiful", "Cute")
)

private val p8 = Puppy(
    id = 8,
    name = "Daisy",
    age = "1 year",
    gender = "male",
    desc = "I love him because he is so cuddly.",
    imageId = R.drawable.puppy_8,
    tags = setOf("Beautiful", "Cute")
)

private val p9 = Puppy(
    id = 9,
    name = "Lucy",
    age = "1 year",
    gender = "female",
    desc = "Sometimes she can be very wobbly on her feet.",
    imageId = R.drawable.puppy_9,
    tags = setOf("Beautiful", "Cute")
)

private val puppies = listOf(
    p1,
    p2,
    p3,
    p4,
    p5,
    p6,
    p7,
    p8,
    p9
)
