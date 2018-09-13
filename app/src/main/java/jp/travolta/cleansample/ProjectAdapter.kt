package jp.travolta.cleansample

import android.databinding.DataBindingUtil
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.travolta.cleansample.databinding.ProjectListItemBinding

class ProjectAdapter(private val projectClickCallback: ProjectClickCallback?) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    internal var projectList: List<Project>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding : ProjectListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.project_list_item, parent, false)

        binding.setCallback(projectClickCallback)

        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.setProject(projectList!![position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (projectList == null) 0 else projectList!!.size
    }

    class ProjectViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface ProjectClickCallback {
        fun onClick(project: Project)
    }
}

