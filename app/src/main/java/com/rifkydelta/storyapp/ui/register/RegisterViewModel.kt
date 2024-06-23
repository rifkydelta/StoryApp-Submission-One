package com.rifkydelta.storyapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rifkydelta.storyapp.data.database.Repository
import com.rifkydelta.storyapp.Response.RegisterResponse
import com.rifkydelta.storyapp.data.di.Event
import kotlinx.coroutines.launch

class RegisterViewModel (private val repository: Repository) : ViewModel() {
    val registerResponse: LiveData<RegisterResponse> = repository.registerResponse
    val toastText: LiveData<Event<String>> = repository.toastText

    fun dataRegister(name: String, email: String, password: String) {
        viewModelScope.launch {
            repository.postRegister(name, email, password)
        }
    }
}