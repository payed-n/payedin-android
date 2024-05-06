package team.payedin.android.ui.trade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import team.payedin.android.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trade_list, container, false)
        //더미값
        val item = listOf(
            PlaceholderContent.PlaceholderItem(
                "asd",
                "asd",
                "asd"
            ),
            PlaceholderContent.PlaceholderItem(
                "asd",
                "asd",
                "asd"
            ),
            PlaceholderContent.PlaceholderItem(
                "asd",
                "asd",
                "asd"
            ),
            PlaceholderContent.PlaceholderItem(
                "asd",
                "asd",
                "asd"
            ),
        )
        val adapter = TradeListRecyclerViewAdapter(item)
        view.findViewById<RecyclerView>(R.id.re).adapter = adapter
        view.findViewById<RecyclerView>(R.id.re).layoutManager = LinearLayoutManager(context)

        return view
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
