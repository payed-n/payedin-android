package team.payedin.android.ui.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.payedin.android.databinding.ItemWalletHistoryBinding
import team.payedin.android.gahasung.response.FetchWalletHistoryResponse

class WalletPayHistoryRecyclerViewAdapter(
    private val values: List<FetchWalletHistoryResponse.History>,
) : RecyclerView.Adapter<WalletPayHistoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWalletHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item.createdAt, item.name, item.balance, item.difference)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: ItemWalletHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(createdAt: String, name: String, balance: Long, difference: Long) {
            binding.tvWalletHistoryItemDate.text = createdAt
            binding.tvWalletHistoryItemTitle.text = name
            binding.tvWalletHistoryItemAmountLeft.text = balance.toString()
            binding.tvWalletHistoryItemTransferAmount.text = difference.toString()
        }
    }
}
