package me.jadjer.ansem.fragments.request_list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import me.jadjer.ansem.fragments.request_list.PlaceholderContent.PlaceholderItem
import me.jadjer.ansem.databinding.FragmentRequestItemBinding

class MyRequestRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
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
        holder.requestId.text = item.requestId
        holder.requestDate.text = item.requestDate
        holder.requestTo.text = item.requestTo
        holder.requestState.text = item.requestState
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentRequestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val requestId: TextView = binding.requestId
        val requestDate: TextView = binding.requestDate
        val requestTo: TextView = binding.requestTo
        val requestState: TextView = binding.requestState
    }

}