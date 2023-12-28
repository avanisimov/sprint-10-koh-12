package ru.yandex.practicum.sprint10koh12

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.lang.IllegalStateException

class ChatItemsAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items: MutableList<ChatItem> = mutableListOf()

    fun updateItems(newItems: List<ChatItem>) {
        val oldItems = items

        val diffResult = DiffUtil.calculateDiff(object: DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldItems.size
            }

            override fun getNewListSize(): Int {
                return newItems.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition] == newItems[newItemPosition]
            }
        })

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = items[position]) {
            is ChatItem.ChatDateChangeItem -> VIEW_TYPE_DATE_CHANGE
            is ChatItem.ChatMessage -> if (item.author == ChatItem.ChatMessage.Author.ME) {
                VIEW_TYPE_MY_MESSAGE
            } else {
                VIEW_TYPE_OTHER_MESSAGE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MY_MESSAGE -> ChatItemMyMessageViewHolder(parent)
            VIEW_TYPE_OTHER_MESSAGE -> ChatItemOtherMessageViewHolder(parent)
            VIEW_TYPE_DATE_CHANGE -> ChatDateChangeItemViewHolder(parent)
            else -> throw IllegalStateException("There is no ViewHolder for viewType=$viewType")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ChatItemMyMessageViewHolder) {
            holder.bind(items[position] as ChatItem.ChatMessage)
        } else if (holder is ChatItemOtherMessageViewHolder) {
            holder.bind(items[position]as ChatItem.ChatMessage)
        } else if (holder is ChatDateChangeItemViewHolder) {
            holder.bind(items[position]as ChatItem.ChatDateChangeItem)
        }
    }

    companion object {
        const val VIEW_TYPE_MY_MESSAGE = 1
        const val VIEW_TYPE_OTHER_MESSAGE = 2
        const val VIEW_TYPE_DATE_CHANGE = 3
    }
}