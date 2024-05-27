package team.payedin.android.ui.trade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import team.payedin.android.R
import team.payedin.android.gahasung.api.ApiProvider
import team.payedin.android.ui.trade.TradeListRecyclerViewAdapter.Companion.tradeId

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TradeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TradeDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trade_details, container, false);
        createView(view)
        view.findViewById<Button>(R.id.detail_request).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    ApiProvider.tradeApi().createTradeRequest(
                        tradeId = tradeId,
                    )
                }.onSuccess {
                    createView(view)
                }
            }
        }
        return view
    }

    @SuppressLint("SetTextI18n")
    fun createView(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                ApiProvider.tradeApi().fetchTradesDetail(tradeId = tradeId)
            }.onSuccess {
                println(it)
                withContext(Dispatchers.Main) {
                    //이미지
                    Glide.with(view.findViewById<ImageView>(R.id.imageView).context)
                        .load(it.trade.imageUrl)
                        .into(view.findViewById(R.id.imageView))

                    //텍스트
                    view.findViewById<TextView>(R.id.detail_title).text = it.trade.title
                    view.findViewById<TextView>(R.id.detail_user_name).text =
                        it.user.gcn + it.user.name
                    view.findViewById<TextView>(R.id.detail_explain).text = it.trade.content
                    view.findViewById<TextView>(R.id.detail_price).text =
                        it.trade.price.toString() + "원"
                    if (it.status != null) {
                        view.findViewById<Button>(R.id.detail_request).text =
                            //it.status.toString()
                        "요청됨"
                        view.findViewById<Button>(R.id.detail_request).isEnabled = false
                    }

                    view.findViewById<ImageButton>(R.id.back).setOnClickListener {
                        val transaction: FragmentTransaction =
                            requireActivity().supportFragmentManager.beginTransaction()
                        val tradeListFragment = TradeListFragment()
                        transaction.replace(R.id.nav_host_fragment_content_main, tradeListFragment)
                        transaction.commit()
                    }
                }
            }.onFailure {
                print(it)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TradeDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TradeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}