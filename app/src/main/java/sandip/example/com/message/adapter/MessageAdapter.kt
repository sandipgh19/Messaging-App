package sandip.example.com.message.adapter

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import sandip.example.com.message.R
import sandip.example.com.message.`object`.Message
import sandip.example.com.message.databinding.LayoutItemMessageBinding
import sandip.example.com.message.utils.DataBoundListAdapter
import sandip.example.com.message.utils.helperUtils.AppExecutors

class MessageAdapter (
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors
) : DataBoundListAdapter<Message, LayoutItemMessageBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): LayoutItemMessageBinding {
        val binding = DataBindingUtil
            .inflate<LayoutItemMessageBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_message,
                parent,
                false,
                dataBindingComponent
            )
        return binding
    }

    override fun bind(binding: LayoutItemMessageBinding, item: Message, position: Int) {
        binding.message = item
    }
}