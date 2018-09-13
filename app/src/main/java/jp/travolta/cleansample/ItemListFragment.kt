package jp.travolta.cleansample

import android.arch.lifecycle.Lifecycle
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.travolta.cleansample.databinding.FragmentItemListBinding

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 */
class ItemListFragment : Fragment() {

    val TAG = "ItemListFragment"

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private lateinit var binding: FragmentItemListBinding

    private lateinit var projectAdapter: ProjectAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list, container, false);

        projectAdapter = ProjectAdapter(projectClickCallback)
        binding.projectList.setAdapter(projectAdapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance() =
                ItemListFragment().apply {
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
