package team.payedin.android.ui.trade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import team.payedin.android.R
import team.payedin.android.gahasung.api.ApiProvider
import team.payedin.android.gahasung.request.CreateTradeRequest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TradeCreationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TradeCreationFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_trade_creation, container, false)
        view.findViewById<Button>(R.id.creation_create_trade).setOnClickListener {
            println("ada")
            CoroutineScope(Dispatchers.Main).launch {
                runCatching {
                    withContext(Dispatchers.IO) {
                        ApiProvider.tradeApi().createTrades(
                            CreateTradeRequest(
                                imageUrl = "https://jobis-store.s3.ap-northeast-2.amazonaws.com/LOGO_IMAGE/companydefault.png",
                                title = view.findViewById<EditText>(R.id.creation_title).text.toString(),
                                content = view.findViewById<EditText>(R.id.creation_explain).text.toString(),
                                price = view.findViewById<EditText>(R.id.creation_price).text.toString()
                                    .toInt(),
                            )
                        )
                    }
                }.onSuccess {
                    println("asdas")
                }.onFailure {
                    println(it)
                }
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TradeCreationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TradeCreationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}