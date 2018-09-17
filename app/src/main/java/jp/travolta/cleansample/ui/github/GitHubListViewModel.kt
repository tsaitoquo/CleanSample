package jp.travolta.cleansample.ui.github

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jp.travolta.cleansample.R
import jp.travolta.cleansample.base.BaseViewModel
import jp.travolta.cleansample.model.Post
import jp.travolta.cleansample.model.Repo
import jp.travolta.cleansample.network.GitHubApi
import jp.travolta.cleansample.network.PostApi
import javax.inject.Inject

class GitHubListViewModel: BaseViewModel() {

    companion object {
        val TAG: String = GitHubListViewModel::class.java.simpleName
    }

    @Inject
    lateinit var githubApi: GitHubApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadRepoList() }

    val repoListAdapter: GitHubListAdapter = GitHubListAdapter()

    init{
        loadRepoList()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadRepoList(){
        subscription = githubApi.getProjectList("tsaitoquo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveRepoListStart() }
                .doOnTerminate { onRetrieveRepoListFinish() }
                .subscribe(
                        { result -> onRetrieveRepoListSuccess(result) },
                        { onRetrieveRepoListError() }
                )
    }

    private fun onRetrieveRepoListStart(){
        Log.d(TAG, "onRetrievePostListStart")
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveRepoListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveRepoListSuccess(postList:List<Repo>){
        Log.d(TAG, "onRetrievePostListSuccess")
        repoListAdapter.updatePostList(postList)
    }

    private fun onRetrieveRepoListError(){
        Log.d(TAG, "onRetrievePostListError")
        errorMessage.value = R.string.post_error
    }
}