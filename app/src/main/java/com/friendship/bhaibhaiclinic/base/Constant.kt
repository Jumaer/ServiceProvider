package com.friendship.bhaibhaiclinic.base


/**
 * This object type is required for
 * any fixed value of project customization
 * Please note that --  only fixed values for whole projects will be there..
 *
 * @author Jumaer Ahamed
 * @since 25th April 2024
 */
object Constant {

    /**
     * Network constants are here..
     * Hence we can change or replace the value from this file
     */
    const val AUTH = "Authorization"
    const val ACCEPT = "Accept"
    const val CONTENT_TYPE = "Content-Type"
    const val APPLICATION_JSON = "application/json"
    const val CACHE_CONTROL = "Cache-Control"
    const val OFF_CACHE_VALUE_HINT = "public, only-if-cached, max-stale="
    const val ONLINE_CACHE_VALUE_HINT = "public, max-age="
    const val REMOVABLE_HEADER = "Pragma"
    const val TYPE_TOKEN = "Bearer"


    /**
     * Project constants are here ..
     * Hence we can change or replace from this place..
     */

    const val INACTIVE = "inactive"
    const val ACTIVE = "active"
    const val USER_TOKEN = "USER_TOKEN"
    const val PREFS_TOKEN_FILE = "PREFS_TOKEN_FILE"

    // Application common tag to test log
    const val TAG = "_TEST_TAG"


    /**
     * For data passing ..
     */

    const val UPDATE = "UPDATE"
    const val CREATE = "CREATE"
}