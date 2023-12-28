package ru.yandex.practicum.sprint10koh12

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.yandex.practicum.sprint10koh12.databinding.ChatItemDayBinding
import ru.yandex.practicum.sprint10koh12.databinding.ChatItemMyMessageBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ChatDateChangeItemViewHolder(
    parentView: ViewGroup,
    val binding: ChatItemDayBinding = ChatItemDayBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : ViewHolder(
    binding.root
) {


    fun bind(item: ChatItem.ChatDateChangeItem) {
        Log.d("ViewHolder", "$this $item")
        binding.text.text = item.text

    }
}