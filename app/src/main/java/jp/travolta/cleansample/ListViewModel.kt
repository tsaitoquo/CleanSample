package jp.travolta.cleansample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import jp.travolta.cleansample.repository.ProjectRepository
import javax.inject.Inject

class ListViewModel @Inject
constructor(application: Application) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    val projectListObservable: LiveData<List<Project>>
    val projectRepository = ProjectRepository.getInstance()

    init {

        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = projectRepository.getProjectList("tsaitoquo")
    }
}
