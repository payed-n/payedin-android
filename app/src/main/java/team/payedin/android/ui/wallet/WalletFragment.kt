package team.payedin.android.ui.wallet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import team.payedin.android.R
import team.payedin.android.databinding.FragmentWalletBinding
import team.payedin.android.gahasung.api.ApiProvider
import team.payedin.android.gahasung.response.FetchWalletHistoryResponse
import team.payedin.android.ui.transfer.TransferAccountSearchFragment
import team.payedin.android.ui.transfer.Wallet

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = mutableListOf<FetchWalletHistoryResponse.History>()
        val recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = WalletPayHistoryRecyclerViewAdapter(result)
        recyclerView.adapter = adapter

        binding.btnWalletTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_WalletFragment_to_TransferFragment)
        }

        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                ApiProvider.walletApi().fetchWalletHistory()
            }.onSuccess { res ->
                withContext(Dispatchers.Main) {
                    result.addAll(res.histories)
                    adapter.notifyDataSetChanged()
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                ApiProvider.walletApi().fetchWallet()
            }.onSuccess { res ->
                withContext(Dispatchers.Main) {
                    binding.tvWalletAccountMoneyAmount.text = res.balance.toString()
                    binding.tvWalletUserAccount.text = res.name + "@" + res.accountNumber
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                ApiProvider.userApi().fetchPoints()
            }.onSuccess { res ->
                withContext(Dispatchers.Main) {
                    binding.tvWalletPlusPoint.text = "상점 ${res.plusPoint}점"
                    binding.tvWalletMinusPoint.text = "벌점 ${res.minusPoint}점"
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
