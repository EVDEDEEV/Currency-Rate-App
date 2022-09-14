package my.project.currenciestestapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import my.project.currenciestestapp.databinding.FilterBottomSheetFragmentBinding

class FilterBottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FilterBottomSheetFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FilterBottomSheetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}