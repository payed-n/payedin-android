package team.payedin.android.ui.trade

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.payedin.android.R

import team.payedin.android.ui.trade.placeholder.PlaceholderContent.PlaceholderItem
import team.payedin.android.databinding.FragmentTradeRequestListBinding
import team.payedin.android.databinding.TradeListItemBinding
import team.payedin.android.databinding.TradeRequestListItemBinding
import team.payedin.android.gahasung.api.ApiProvider

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class TradeRequestListRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
) : RecyclerView.Adapter<TradeRequestListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            TradeRequestListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.content
        Glide.with(holder.imgView.context)
            .load(item.img)
            .into(holder.imgView)

        holder.approve.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    println(item.id)
                    ApiProvider.tradeApi().approveTrade(id = item.tradeId, approve = true)
                }
            }
        }
        holder.reject.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    ApiProvider.tradeApi().approveTrade(id = item.id, approve = false)
                }
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: TradeRequestListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgView: ImageView = binding.tradeRequestImage
        val contentView: TextView = binding.tradeRequestContent
        val approve: Button = binding.tradeRequestApprove
        val reject: Button = binding.tradeRequestReject

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}