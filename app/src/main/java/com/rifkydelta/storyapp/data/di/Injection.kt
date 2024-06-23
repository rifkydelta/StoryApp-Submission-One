package com.rifkydelta.storyapp.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.rifkydelta.storyapp.data.database.Repository
import com.rifkydelta.storyapp.data.preference.SessionPreferences
import com.rifkydelta.storyapp.data.retrofit.ApiConfig

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")

object Injection {
    fun provideRepository(context: Context): Repository {
        val preferences = SessionPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(preferences, apiService)
    }
}