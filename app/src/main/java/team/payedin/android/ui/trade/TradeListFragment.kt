package team.payedin.android.ui.trade

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import team.payedin.android.R
import team.payedin.android.gahasung.api.ApiProvider
import team.payedin.android.ui.trade.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class TradeListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val item: MutableList<PlaceholderContent.PlaceholderItem> = mutableListOf()
        val view = inflater.inflate(R.layout.fragment_trade_list, container, false)

        updateView(item = item, view = view)
        view.findViewById<ImageView>(R.id.notification).setOnClickListener {
            val transaction: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            val tradeRequestListFragment = TradeRequestListFragment()
            transaction.replace(
                R.id.nav_host_fragment_content_main,
                tradeRequestListFragment
            )
            transaction.commit()
        }
        return view
    }

    private fun delete(
        id: String,
        view: View,
        item: MutableList<PlaceholderContent.PlaceholderItem>,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                ApiProvider.tradeApi().deleteTrade(id)
            }.onFailure {
                updateView(item, view)
            }
        }
    }

    private fun updateView(
        item: MutableList<PlaceholderContent.PlaceholderItem>,
        view: View,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                ApiProvider.tradeApi().fetchTrades()
            }.onSuccess { response ->
                println(response)
                item.clear()
                response.trade.forEach { trade ->
                    item.add(
                        PlaceholderContent.PlaceholderItem(
                            id = trade.tradeId,
                            content = trade.title,
                            details = trade.nickname,
                            img = trade.imageUrl,
                            tradeId = trade.tradeId,
                        )
                    )
                }
                withContext(Dispatchers.Main) {
                    view.findViewById<ImageView>(R.id.add).setOnClickListener {
                        val transaction: FragmentTransaction =
                            requireActivity().supportFragmentManager.beginTransaction()
                        val tradeCreationFragment = TradeCreationFragment()
                        transaction.replace(
                            R.id.nav_host_fragment_content_main,
                            tradeCreationFragment
                        )
                        transaction.commit()
                    }
                    if (item.isNotEmpty()) {
                        val adapter = TradeListRecyclerViewAdapter(
                            item,
                            onDelete = { delete(it.id, view, item) },
                            onItemClick = {
                                val transaction: FragmentTransaction =
                                    requireActivity().supportFragmentManager.beginTransaction()
                                val tradeDetailsFragment = TradeDetailsFragment()
                                transaction.replace(
                                    R.id.nav_host_fragment_content_main,
                                    tradeDetailsFragment
                                )
                                transaction.commit()
                            }
                        )
                        view.findViewById<RecyclerView>(R.id.re).adapter = adapter
                        view.findViewById<RecyclerView>(R.id.re).layoutManager =
                            LinearLayoutManager(context)
                    }
                }
            }.onFailure {
                println(it)
            }
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TradeListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
