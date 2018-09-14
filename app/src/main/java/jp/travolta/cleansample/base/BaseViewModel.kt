package jp.travolta.cleansample.base

import android.arch.lifecycle.ViewModel
import jp.travolta.cleansample.network.PostApi
import javax.inject.Inject

class BaseViewModel: ViewModel() {
    @Inject
    lateinit var postApi: PostApi
}