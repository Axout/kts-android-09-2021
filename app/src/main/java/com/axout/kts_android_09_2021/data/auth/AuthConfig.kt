package com.axout.kts_android_09_2021.data.auth

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://www.strava.com/oauth/mobile/authorize"
    const val TOKEN_URI = "https://www.strava.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "activity:read_all"

    const val CLIENT_ID = "72136"
    const val CLIENT_SECRET = "b1fa9743beffadc3d98af880b2893b12c4276837"
    const val CALLBACK_URL = "school://kts.studio/callback"
}