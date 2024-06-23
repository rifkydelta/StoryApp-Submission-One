package  com.rifkydelta.storyapp.ui.addStory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rifkydelta.storyapp.Response.AddStoryResponse
import com.rifkydelta.storyapp.data.database.Repository
import com.rifkydelta.storyapp.data.di.Event
import com.rifkydelta.storyapp.data.preference.SessionModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddStoryViewModel(private val repository: Repository) : ViewModel() {
    val uploadResponse: LiveData<AddStoryResponse> = repository.uploadResponse
    val isLoading: LiveData<Boolean> = repository.isLoading
    val toastText: LiveData<Event<String>> = repository.toastText

    fun uploadStory(token: String, file: MultipartBody.Part, description: RequestBody) {
        viewModelScope.launch {
            repository.uploadStory(token, file, description)
        }
    }

    fun getSession(): LiveData<SessionModel> {
        return repository.getSession()
    }
}