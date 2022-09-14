package my.project.currenciestestapp.presentation.fragments.filterFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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

//    private fun initFilterByName() {
//        binding.byCurrencyNameFilter.setOnCheckedChangeListener()
//    }
}