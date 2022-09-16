package my.project.currenciestestapp.presentation.fragments.filterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import my.project.currenciestestapp.Constants.NAME_ASC
import my.project.currenciestestapp.Constants.NAME_DESC
import my.project.currenciestestapp.Constants.RATE_ASC
import my.project.currenciestestapp.Constants.RATE_DESC
import my.project.currenciestestapp.Constants.REQUEST_KEY
import my.project.currenciestestapp.databinding.FragmentFilterBottomSheetBinding

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFilterByNameAscending()
        initFilterByNameDescending()
        initFilterByRateAscending()
        initFilterByRateDescending()
    }

    private fun initFilterByNameAscending() {
        binding.ascendingName.setOnClickListener {
            val result = NAME_ASC
            setFragmentResult(REQUEST_KEY, bundleOf(NAME_ASC to result))
            activity?.onBackPressed()
        }
    }

    private fun initFilterByNameDescending() {
        binding.descendingName.setOnClickListener {
            val result = NAME_DESC
            setFragmentResult(REQUEST_KEY, bundleOf(NAME_DESC to result))
            activity?.onBackPressed()
        }
    }

    private fun initFilterByRateAscending() {
        binding.ascendingRate.setOnClickListener {
            val result = RATE_ASC
            setFragmentResult(REQUEST_KEY, bundleOf(RATE_ASC to result))
            activity?.onBackPressed()
        }
    }

    private fun initFilterByRateDescending() {
        binding.descendingRate.setOnClickListener {
            val result = RATE_DESC
            setFragmentResult(REQUEST_KEY, bundleOf(RATE_DESC to result))
            activity?.onBackPressed()
        }
    }
}