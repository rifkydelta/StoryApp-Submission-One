package com.rifkydelta.storyapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rifkydelta.storyapp.data.preference.SessionModel
import com.rifkydelta.storyapp.data.database.Repository
import com.rifkydelta.storyapp.Response.ListStoryItem
import com.rifkydelta.storyapp.data.di.Event
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val toastText: LiveData<Event<String>> = repository.toastText
    val getListStories: LiveData<PagingData<ListStoryItem>> =
        repository.getStories().cachedIn(viewModelScope)

    fun getSession(): LiveData<SessionModel> {
        return repository.getSession()
    }

    fun logoutApp() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}