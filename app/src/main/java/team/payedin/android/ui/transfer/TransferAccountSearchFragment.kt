package team.payedin.android.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import team.payedin.android.R
import team.payedin.android.databinding.FragmentTransferAccountSearchBinding

class TransferAccountSearchFragment : Fragment() {

    private var _binding: FragmentTransferAccountSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTransferAccountSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnTransferDepositAccountNumber.setOnClickListener {
            Wallet.currentAccount = binding.etTransferDepositAccountNumber.text.toString()
            findNavController().navigate(R.id.action_Search_to_Deposit)
        }
    }
}