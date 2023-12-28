package ru.yandex.practicum.sprint10koh12

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.yandex.practicum.sprint10koh12.databinding.ChatItemMyMessageBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ChatItemMyMessageViewHolder(
    parentView: ViewGroup,
    val binding: ChatItemMyMessageBinding = ChatItemMyMessageBinding.inflate(
        LayoutInflater.from(parentView.context), parentView, false
    )
) : ViewHolder(
    binding.root
) {

    val dateFormat = SimpleDateFormat("H:mm", Locale.getDefault())

    fun bind(item: ChatItem.ChatMessage) {
        Log.d("ViewHolder", "$this $item")
        binding.text.text = item.text
        binding.date.text = dateFormat.format(item.date)
        when (item.status) {
            ChatItem.ChatMessage.Status.DELIVERED -> {
                binding.status.visibility = View.VISIBLE
                binding.status.setImageResource(R.drawable.delivered_sign)
            }

            ChatItem.ChatMessage.Status.READ -> {
                binding.status.visibility = View.VISIBLE
                binding.status.setImageResource(R.drawable.read_sign)
            }

            ChatItem.ChatMessage.Status.SENT -> {
                binding.status.visibility = View.VISIBLE
                binding.status.setImageResource(R.drawable.single_tick)
            }

            ChatItem.ChatMessage.Status.NOT_SENT -> binding.status.visibility = View.GONE
        }
    }
}