package com.camachoyury.instapic

import com.google.firebase.database.Exclude
import java.io.Serializable


class User : Serializable {
    var uid: String? = null
    var name: String? = null
    var email: String? = null

    @Exclude
    var isAuthenticated = false

    @Exclude
    var isNew = false

    @Exclude
    var isCreated = false

    constructor() {}
     constructor(uid: String?, name: String?, email: String?) {
        this.uid = uid
        this.name = name
        this.email = email
    }
}