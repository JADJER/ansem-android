package me.jadjer.ansem.fragments.request_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import me.jadjer.ansem.R
import me.jadjer.ansem.data.model.entity.RequestEntity
import me.jadjer.ansem.databinding.FragmentRequestDetailBinding


class RequestDetailFragment : Fragment() {

    private var item: RequestEntity? = null

    private lateinit var viewModel: RequestDetailViewModel
    private lateinit var _binding: FragmentRequestDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
//            if (it.containsKey(ARG_ITEM_ID)) {
//                item = RequestDao.getRequest(it.getInt(ARG_ITEM_ID))
//            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestDetailBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true);
        updateContent()

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RequestDetailViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.request_detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updateContent() {
//        toolbarLayout?.title = item?.content
//
//        // Show the placeholder content as text in a TextView.
//        item?.let {
//            itemDetailTextView.text = it.details
//        }
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}