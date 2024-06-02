package team.payedin.android.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.payedin.android.R
import team.payedin.android.databinding.FragmentTransferDepositBinding
import team.payedin.android.gahasung.api.ApiProvider
import team.payedin.android.gahasung.request.TransferRequest

class TransferDepositFragment : Fragment() {
    private var _binding: FragmentTransferDepositBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTransferDepositBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnTransferDepositAccountNumber.setOnClickListener {
            Wallet.sendAmount = binding.etTransferDepositAccountNumber.text.toString()
            runCatching {

                val account = Wallet.currentAccount
                val amount = Wallet.sendAmount

                CoroutineScope(Dispatchers.IO).launch {
                    ApiProvider.walletApi().transfer(
                        TransferRequest(
                            targetAccountNumber = account,
                            amount = amount.toLong(),
                        ),
                    )
                }
            }.onSuccess {
                findNavController().navigate(R.id.action_Deposit_to_Result)
            }.onFailure {
                Toast.makeText(context, "송금할 수 없습니다. 계좌를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}