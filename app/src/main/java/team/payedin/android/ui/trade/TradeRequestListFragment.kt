package team.payedin.android.ui.trade

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class TradeRequestListFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_trade_request_list, container, false)

        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                ApiProvider.tradeApi().fetchTradeRequests()
            }.onSuccess {
                it.requests.forEach {
                    item.add(
                        PlaceholderContent.PlaceholderItem(
                            content = it.nickname + "님이" + it.title + "공고에\n 대해 거래를 요청하셨습니다.",
                            details = it.nickname,
                            img = it.imageUrl,
                            id = it.tradeId,
                            tradeId = it.tradeRequestId,
                        )
                    )
                }
                withContext(Dispatchers.Main) {
                    if (item.isNotEmpty()) {
                        val adapter = TradeRequestListRecyclerViewAdapter(item) {
                            val transaction: FragmentTransaction =
                                requireActivity().supportFragmentManager.beginTransaction()
                            val tradeCreationFragment = TradeListFragment()
                            transaction.replace(
                                R.id.nav_host_fragment_content_main,
                                tradeCreationFragment
                            )
                            transaction.commit()
                            CoroutineScope(Dispatchers.IO).launch {
                                ApiProvider.tradeApi().fetchTradeRequests()
                            }
                        }
                        view.findViewById<RecyclerView>(R.id.request_re).adapter = adapter
                        view.findViewById<RecyclerView>(R.id.request_re).layoutManager =
                            LinearLayoutManager(context)
                    }
                }
            }
        }

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TradeRequestListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}