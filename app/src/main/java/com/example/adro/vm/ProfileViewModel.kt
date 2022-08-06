package com.example.adro.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    fun getTextDetail():String{
        return "hello android"
    }
}