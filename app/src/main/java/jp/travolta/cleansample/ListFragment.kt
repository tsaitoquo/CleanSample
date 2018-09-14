package jp.travolta.cleansample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.travolta.cleansample.databinding.FragmentListBinding
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 */
class ListFragment : Fragment() {

    val TAG = "ListFragment"

    private var columnCount = 1

    @Inject lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private lateinit var binding: FragmentListBinding

    private lateinit var projectAdapter: ProjectAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);

        projectAdapter = ProjectAdapter(projectClickCallback)
        binding.projectList.setAdapter(projectAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java!!)
        val viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java!!)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ListViewModel) {
        // Update the list when the data changes
        viewModel.projectListObservable.observe(this, Observer<List<Project>> { projects ->
            if (projects != null) {
                binding.isLoading = false
                projectAdapter.setProjectList(projects)
            }
        })
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance() =
                ListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, 1)
                    }
                }
    }

    private val projectClickCallback = object: ProjectAdapter.ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
//                (activity as MainActivity).show(project)
                Log.d(TAG, "clicked Project")
            }
        }
    }
}
