package jp.travolta.cleansample.ui.github

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.travolta.cleansample.R
import jp.travolta.cleansample.databinding.ItemRepoBinding
import jp.travolta.cleansample.model.Repo

class GitHubListAdapter: RecyclerView.Adapter<GitHubListAdapter.ViewHolder>() {
    private lateinit var list:List<Repo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubListAdapter.ViewHolder {
        val binding: ItemRepoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitHubListAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return if(::list.isInitialized) list.size else 0
    }

    fun updatePostList(postList:List<Repo>){
        this.list = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root){

        private val viewModel = GitHubViewModel()

        fun bind(repo: Repo){
            viewModel.bind(repo)
            binding.viewModel = viewModel
        }
    }
}
