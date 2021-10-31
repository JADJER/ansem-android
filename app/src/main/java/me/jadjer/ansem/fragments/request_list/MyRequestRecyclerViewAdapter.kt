package me.jadjer.ansem.fragments.request_list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.jadjer.ansem.data.model.entity.RequestEntity

import me.jadjer.ansem.databinding.FragmentRequestItemBinding
import android.content.Context
import androidx.appcompat.widget.PopupMenu
import me.jadjer.ansem.R


class MyRequestRecyclerViewAdapter(
    private val context: Context,
    private val values: List<RequestEntity>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<MyRequestRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentRequestItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.requestId.text = item.requestId.toString()
//        holder.requestDate.text = item.requestDate
//        holder.requestTo.text = item.requestTo
//        holder.requestState.text = item.requestState

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }

        holder.buttonViewOption.setOnClickListener {
            val popup = PopupMenu(context, holder.buttonViewOption)
            popup.inflate(R.menu.request_detail_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_request_delete -> {

                    }
                }
                false
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentRequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val requestId: TextView = binding.requestId
        val requestDate: TextView = binding.requestDate
        val requestTo: TextView = binding.requestTo
        val requestState: TextView = binding.requestState
        val buttonViewOption: TextView = binding.buttonViewOptions
    }

}