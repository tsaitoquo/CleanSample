package jp.travolta.cleansample.network

import io.reactivex.Observable
import jp.travolta.cleansample.model.Post
import jp.travolta.cleansample.model.Repo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * The interface which provides methods to get result of webservices
 */
interface GitHubApi {
    //一覧
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Observable<List<Repo>>

    //詳細
    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Observable<Repo>

}

class HubsRetro(val retrofit: Retrofit) {
}