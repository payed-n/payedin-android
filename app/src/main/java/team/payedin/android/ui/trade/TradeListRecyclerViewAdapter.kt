package team.payedin.android.ui.trade

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import team.payedin.android.R
import team.payedin.android.databinding.TradeListItemBinding
import team.payedin.android.ui.trade.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class TradeListRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
    private val onItemClick: (PlaceholderItem) -> Unit,
    private val onDelete: (PlaceholderItem) -> Unit,
) : RecyclerView.Adapter<TradeListRecyclerViewAdapter.ViewHolder>() {

    companion object {
        var tradeId: String = ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            TradeListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.content
        holder.contentView.text = item.details

        // Use Glide to load the image
        Glide.with(holder.image.context)
            .load(item.img)
            .into(holder.image)
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: TradeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.title
        val contentView: TextView = binding.content
        val image: ImageView = binding.imgTrade

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(values[position])
                    tradeId = values[position].id
                }
            }
            binding.root.findViewById<ImageView>(R.id.icon_delete).setOnClickListener {
                val position = adapterPosition
                onDelete(values[position])
            }
        }


        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}