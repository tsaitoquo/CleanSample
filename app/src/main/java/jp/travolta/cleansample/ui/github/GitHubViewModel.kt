package jp.travolta.cleansample.ui.github

import android.arch.lifecycle.MutableLiveData
import jp.travolta.cleansample.base.BaseViewModel
import jp.travolta.cleansample.model.Repo

class GitHubViewModel: BaseViewModel() {
    private val repoTitle = MutableLiveData<String>()
    private val repoBody = MutableLiveData<String>()

    fun bind(repo: Repo){
        repoTitle.value = repo.name
        repoBody.value = repo.id
    }

    fun getRepoTitle():MutableLiveData<String>{
        return repoTitle
    }

    fun getRepoBody(): MutableLiveData<String> {
        return repoBody
    }
}