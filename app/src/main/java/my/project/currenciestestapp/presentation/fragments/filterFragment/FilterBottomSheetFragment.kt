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

    private fun pasteCodeForFragmentResultBoilerplate(requestKey: String, filterParam: String) {
        setFragmentResult(requestKey,
            bundleOf(filterParam to filterParam))
        dismiss()
    }

    private fun initFilterByNameAscending() {
        binding?.ascendingName?.setOnClickListener {
            pasteCodeForFragmentResultBoilerplate(REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_ASC,
                NAME_ASC_ARG)
        }
    }

    private fun initFilterByNameDescending() {
        binding?.descendingName?.setOnClickListener {
            pasteCodeForFragmentResultBoilerplate(REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_DESC,
                NAME_DESC_ARG)
        }
    }

    private fun initFilterByRateAscending() {
        binding?.ascendingRate?.setOnClickListener {
            pasteCodeForFragmentResultBoilerplate(REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_ASC,
                RATE_ASC_ARG)
        }
    }

    private fun initFilterByRateDescending() {
        binding?.descendingRate?.setOnClickListener {
            pasteCodeForFragmentResultBoilerplate(REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_DESC,
                RATE_DESC_ARG)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}