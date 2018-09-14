package jp.travolta.cleansample.ui.post

import jp.travolta.cleansample.base.BaseViewModel
import jp.travolta.cleansample.network.PostApi
import javax.inject.Inject

class PostListViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
}