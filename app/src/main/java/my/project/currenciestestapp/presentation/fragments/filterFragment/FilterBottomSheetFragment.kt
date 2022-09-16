package my.project.currenciestestapp.presentation.fragments.filterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import my.project.currenciestestapp.Constants.NAME_ASC_ARG
import my.project.currenciestestapp.Constants.NAME_DESC_ARG
import my.project.currenciestestapp.Constants.RATE_ASC_ARG
import my.project.currenciestestapp.Constants.RATE_DESC_ARG
import my.project.currenciestestapp.Constants.REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_ASC
import my.project.currenciestestapp.Constants.REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_DESC
import my.project.currenciestestapp.Constants.REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_ASC
import my.project.currenciestestapp.Constants.REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_DESC
import my.project.currenciestestapp.databinding.FragmentFilterBottomSheetBinding

class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var binding: FragmentFilterBottomSheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFilterByNameAscending()
        initFilterByNameDescending()
        initFilterByRateAscending()
        initFilterByRateDescending()
    }

    private fun initFilterByNameAscending() {
        binding?.ascendingName?.setOnClickListener {
            val result = NAME_ASC_ARG
            setFragmentResult(REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_ASC,
                bundleOf(NAME_ASC_ARG to result))
            dismiss()
        }
    }

    private fun initFilterByNameDescending() {
        binding?.descendingName?.setOnClickListener {
            val result = NAME_DESC_ARG
            setFragmentResult(REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_DESC,
                bundleOf(NAME_DESC_ARG to result))
            dismiss()
        }
    }

    private fun initFilterByRateAscending() {
        binding?.ascendingRate?.setOnClickListener {
            val result = RATE_ASC_ARG
            setFragmentResult(REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_ASC,
                bundleOf(RATE_ASC_ARG to result))
            dismiss()
        }
    }

    private fun initFilterByRateDescending() {
        binding?.descendingRate?.setOnClickListener {
            val result = RATE_DESC_ARG
            setFragmentResult(REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_DESC,
                bundleOf(RATE_DESC_ARG to result))
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}