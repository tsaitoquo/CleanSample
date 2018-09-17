package jp.travolta.cleansample.base

import android.arch.lifecycle.ViewModel
import jp.travolta.cleansample.injection.component.DaggerViewModelInjector
import jp.travolta.cleansample.injection.component.ViewModelInjector
import jp.travolta.cleansample.injection.module.NetworkModule
import jp.travolta.cleansample.network.PostApi
import jp.travolta.cleansample.ui.github.GitHubListViewModel
import jp.travolta.cleansample.ui.github.GitHubViewModel
import jp.travolta.cleansample.ui.post.PostListViewModel
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.injectPost(this)
            is GitHubListViewModel -> injector.injectHubs(this)
        }
    }
}