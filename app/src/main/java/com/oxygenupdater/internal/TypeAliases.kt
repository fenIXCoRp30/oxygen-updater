package com.oxygenupdater.internal

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

val objectMapper: ObjectMapper = jacksonObjectMapper().registerModule(
    // coerce strings to booleans
    SimpleModule().addDeserializer(Boolean::class.java, BooleanDeserializer())
).setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
// ^ Tell ObjectMapper to convert camelCase to snake_case (Server API response have fields in snake case)
// This helps us avoid annotating every field with `@JsonProperty` unnecessarily

/**
 * Generic Kotlin callback. This used to be [java.util.function.Consumer] in Java
 */
typealias KotlinCallback<E> = (E) -> Unit

/**
 * Generic Kotlin function. This used to be [java.util.function.Function] in Java
 */
typealias KotlinFunction<T, R> = (T) -> R

typealias NewsItemReadStatusChangedListener = (
    newsItemId: Long,
    isRead: Boolean
) -> Unit

typealias NewsListChangedListener = (
    unreadCount: Int,
    isEmpty: Boolean
) -> Unit

typealias OnPurchaseFinishedListener = (responseCode: Int, purchase: com.android.billingclient.api.Purchase?) -> Unit
