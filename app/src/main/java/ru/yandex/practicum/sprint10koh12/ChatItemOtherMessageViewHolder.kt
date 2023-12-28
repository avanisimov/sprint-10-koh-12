package ru.yandex.practicum.sprint10koh12

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.practicum.sprint10koh12.databinding.ChatItemOtherMessageBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ChatItemOtherMessageViewHolder(
    parentView: ViewGroup,
    val binding: ChatItemOtherMessageBinding = ChatItemOtherMessageBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : RecyclerView.ViewHolder(
    binding.root
) {

    val dateFormat = SimpleDateFormat("H:mm", Locale.getDefault())

    fun bind(item: ChatItem.ChatMessage) {
        Log.d("ViewHolder", "$this $item")
        binding.text.text = item.text
        binding.date.text = dateFormat.format(item.date)

    }

    companion object {
        const val RADUIS = 10
    }
}