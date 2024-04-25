package com.friendship.bhaibhaiclinic.model
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.io.Serializable


/**
 *
 * This is the data object to access data from server
 * can only be used as provider object ..
 *
 * @author Jumaer Ahamed
 * @since 25th April 2024
 */

@Keep
data class SingleProviderResponse(
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("status")
    val status: String?
): Serializable