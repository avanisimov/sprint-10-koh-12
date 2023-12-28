package ru.yandex.practicum.sprint10koh12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yandex.practicum.sprint10koh12.databinding.ActivityMainBinding
import java.util.Date
import java.util.UUID

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.chatMessages.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            true
        )
        val chatItemsAdapter = ChatItemsAdapter()
        binding.chatMessages.adapter = chatItemsAdapter

        val chatItems = mutableListOf<ChatItem>()

        chatItems.addAll(
            listOf(
                ChatItem.ChatMessage(
                    text = "Hope u r well?",
                    date = Date(),
                    status = ChatItem.ChatMessage.Status.SENT,
                    author = ChatItem.ChatMessage.Author.ME
                ),
                ChatItem.ChatMessage(
                    text = "How are you?",
                    date = Date(),
                    status = ChatItem.ChatMessage.Status.DELIVERED,
                    author = ChatItem.ChatMessage.Author.ME
                ),
                ChatItem.ChatMessage(
                    text = "Hi",
                    date = Date(),
                    status = ChatItem.ChatMessage.Status.READ,
                    author = ChatItem.ChatMessage.Author.ME
                ),
            )
        )

        chatItemsAdapter.updateItems(
            chatItems
        )

        binding.inputText.doAfterTextChanged {
            if (it.isNullOrBlank()) {
                binding.footerAction.setImageResource(R.drawable.ic_audio)
            } else {
                binding.footerAction.setImageResource(R.drawable.ic_send)
            }
        }
        binding.footerAction.setOnClickListener {
            val newChatMessageText = binding.inputText.text
            if (!newChatMessageText.isNullOrBlank()) {
                val isAnotherDay = true
                if (isAnotherDay) {
                    chatItems.add(
                        0,
                        ChatItem.ChatDateChangeItem(
                            text = "Today"
                        )
                    )
                }
                chatItems.add(
                    0,
                    ChatItem.ChatMessage(
                        text = newChatMessageText.toString(),
                        date = Date(),
                        status = ChatItem.ChatMessage.Status.NOT_SENT,
                        author = ChatItem.ChatMessage.Author.ME
                    )
                )
                chatItemsAdapter.updateItems(
                    chatItems
                )
                binding.chatMessages.scrollToPosition(0)
//                chatItemsAdapter.notifyItemInserted(0)
                binding.inputText.setText("")
                binding.root.postDelayed({
                    chatItems.add(
                        0,
                        ChatItem.ChatMessage(
                            text = "OK, $newChatMessageText",
                            date = Date(),
                            status = ChatItem.ChatMessage.Status.NOT_SENT,
                            author = ChatItem.ChatMessage.Author.OTHER
                        )
                    )
                    chatItemsAdapter.updateItems(
                        chatItems
                    )
                    binding.chatMessages.scrollToPosition(0)
//                    chatItemsAdapter.notifyItemInserted(0)
                }, 2000)

            }
        }
    }
}