package me.jadjer.ansem.fragments.request_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import me.jadjer.ansem.R
import me.jadjer.ansem.data.model.entity.RequestEntity
import me.jadjer.ansem.databinding.FragmentRequestListBinding
import me.jadjer.ansem.fragments.request_detail.RequestDetailFragment
import me.jadjer.ansem.utils.Event
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class RequestListFragment : Fragment() {

    private val requestListViewModel: RequestListViewModel by viewModel()

    private lateinit var binding: FragmentRequestListBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequestListBinding.inflate(layoutInflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Click Listener to trigger navigation based on if you have
         * a single pane layout or two pane layout
         */
        val onClickListener = View.OnClickListener { itemView ->
            val item = itemView.tag as RequestEntity

            val bundle = Bundle()
            bundle.putInt(RequestDetailFragment.ARG_ITEM_ID, item.requestId)

            findNavController().navigate(
                R.id.action_requestListFragment_to_requestDetailFragment,
                bundle
            )
        }

        val refresh = binding.swipeRefreshLayout

        refresh.setOnRefreshListener {
            requestListViewModel.update()
        }

        requestListViewModel.updateEvent.observe(viewLifecycleOwner, { event ->
            when (event.status) {
                Event.Status.LOADING -> {
                    refresh.isRefreshing = true
                }
                Event.Status.SUCCESS -> {
                    recyclerView.adapter = MyRequestRecyclerViewAdapter(
                        context!!,
                        event.data!!,
                        onClickListener
                    )
                    refresh.isRefreshing = false
                }
                Event.Status.ERROR -> {
                    showUpdateFailed(event.message)
                    refresh.isRefreshing = false
                }
            }
        })

        requestListViewModel.update()
    }

    private fun showUpdateFailed(error: String?) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }
}