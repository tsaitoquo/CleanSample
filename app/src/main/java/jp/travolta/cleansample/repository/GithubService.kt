package jp.travolta.cleansample.repository

import jp.travolta.cleansample.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubService {

    //一覧
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    //詳細
    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>

    companion object {

        //Retrofitインターフェース(APIリクエストを管理)
        val HTTPS_API_GITHUB_URL = "https://api.github.com/"
    }

}
