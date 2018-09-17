package jp.travolta.cleansample.injection.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import jp.travolta.cleansample.network.GitHubApi
import jp.travolta.cleansample.network.HubsRetro
import jp.travolta.cleansample.network.PostApi
import jp.travolta.cleansample.util.GITHUB_API_URL
import jp.travolta.cleansample.util.POST_API_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object GitHubApiModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGitHubApi(hubretro: HubsRetro): GitHubApi {
        return hubretro.retrofit.create(GitHubApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGitHubRetrofitInterface(): HubsRetro {
        return HubsRetro(Retrofit.Builder()
                .baseUrl(GITHUB_API_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build())
    }
}