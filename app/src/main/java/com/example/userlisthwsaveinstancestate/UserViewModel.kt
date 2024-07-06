package com.example.userlisthwsaveinstancestate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    var listUsers: MutableList<User> = mutableListOf()
    val userListViewModel: MutableLiveData<User> by lazy { MutableLiveData<User>() }

}