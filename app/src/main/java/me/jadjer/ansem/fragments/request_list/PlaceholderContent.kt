package me.jadjer.ansem.fragments.request_list

import java.util.ArrayList
import java.util.HashMap


object PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    private val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    private const val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createPlaceholderItem(i))
        }
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP[item.requestId] = item
    }

    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        return PlaceholderItem(
            position.toString(),
            "Отряд 3",
            "10.11.2023",
            "На рассмотрении"
        )
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(
        val requestId: String,
        val requestTo: String,
        val requestDate: String,
        val requestState: String
    )
}