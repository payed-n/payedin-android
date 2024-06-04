package team.payedin.android.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import team.payedin.android.R
import team.payedin.android.databinding.FragmentTransferResultBinding

class TransferResultFragment : Fragment() {
    private var _binding: FragmentTransferResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTransferResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnTransferDepositAccountNumber.setOnClickListener {
            findNavController().navigate(R.id.action_Result_to_Wallet)
        }
    }
}
