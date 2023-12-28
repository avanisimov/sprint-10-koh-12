package ru.yandex.practicum.sprint10koh12

import java.util.Date
import java.util.UUID


sealed class ChatItem {

    abstract val id: String
    data class ChatMessage(
        override val id: String = UUID.randomUUID().toString(),
        val text: String,
//    val date: Long, //  1703694294000
        val date: Date,
        val status: Status,
        val author: Author,
    ) : ChatItem() {

        enum class Status {
            DELIVERED, READ, SENT, NOT_SENT
        }

        enum class Author {
            ME, OTHER
        }
    }

    data class ChatDateChangeItem(
        override val id: String = UUID.randomUUID().toString(),
        val text: String,
    ) : ChatItem()
}

