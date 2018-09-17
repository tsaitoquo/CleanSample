package jp.travolta.cleansample.injection.component

import dagger.Component
import jp.travolta.cleansample.injection.module.GitHubApiModule
import jp.travolta.cleansample.injection.module.NetworkModule
import jp.travolta.cleansample.ui.github.GitHubListViewModel
import jp.travolta.cleansample.ui.post.PostListViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class),(GitHubApiModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified GitHubListViewModel.
     * @param postListViewModel GitHubListViewModel in which to inject the dependencies
     */
    fun injectPost(postListViewModel: PostListViewModel)

    /**
     * Injects required dependencies into the specified GitHubListViewModel.
     * @param postListViewModel GitHubListViewModel in which to inject the dependencies
     */
    fun injectHubs(gitHubListViewModel: GitHubListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun gitHubApiModule(gitHubApiModule: GitHubApiModule): Builder
    }
}