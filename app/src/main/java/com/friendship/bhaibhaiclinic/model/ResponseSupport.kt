package com.friendship.bhaibhaiclinic.model
import androidx.annotation.Keep
import java.io.Serializable

/**
 *
 * This is the list of data object support of the fixed feature of project..
 * @author Jumaer Ahamed
 * @since 25th April 2024
 */

// can only be used as provider object list..
class ProviderListResponse : ArrayList<SingleProviderResponse>()


// It will help to change or replace the object..
// will support for list or item playing
@Keep
data class ProviderItem(
    var email: String?,
    var gender: String?,
    var id: Int?,
    var name: String?,
    var status: String?
): Serializable