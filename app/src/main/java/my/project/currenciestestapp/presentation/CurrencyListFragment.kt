package my.project.currenciestestapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import my.project.currenciestestapp.R
import my.project.currenciestestapp.data.repository.CurrencyRepositoryImpl
import my.project.currenciestestapp.databinding.FragmentCurrencyListBinding

@AndroidEntryPoint
class CurrencyListFragment: Fragment() {

    private val viewModel: SharedViewModel by viewModels()
    private lateinit var binding: FragmentCurrencyListBinding

//    companion object {
//        fun newInstance() = CurrencyListFragment()
//    }

//    private val mainViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container,false)
        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////        viewModel = ViewModelProvider(this).get(CurrencyListViewModel::class.java)
//        // TODO: Use the ViewModel
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

//        runBlocking {
//            Log.d("aboba", "${viewModel.getList()}")
//        }
    }


}