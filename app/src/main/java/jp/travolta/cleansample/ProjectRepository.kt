package jp.travolta.cleansample

import android.arch.lifecycle.LiveData

interface ProjectRepository {
    fun getProjectList(userId: String): LiveData<List<Project>>

}
