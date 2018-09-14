package jp.travolta.cleansample.ui.post

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import jp.travolta.cleansample.R
import jp.travolta.cleansample.base.BaseViewModel
import jp.travolta.cleansample.network.PostApi
import javax.inject.Inject

class PostListViewModel: BaseViewModel() {

    companion object {
        val TAG: String = PostListViewModel::class.java.simpleName
    }

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts(){
        subscription = postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { onRetrievePostListSuccess() },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart(){
        Log.d(TAG, "onRetrievePostListStart")
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(){
        Log.d(TAG, "onRetrievePostListSuccess")

    }

    private fun onRetrievePostListError(){
        Log.d(TAG, "onRetrievePostListError")
        errorMessage.value = R.string.post_error
    }
}