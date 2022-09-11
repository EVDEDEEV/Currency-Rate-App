package my.project.currenciestestapp.presentation.fragments.currencyFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import my.project.currenciestestapp.databinding.FragmentCurrencyListBinding

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private val viewModel: CurrencyListViewModel by viewModels()
    private lateinit var binding: FragmentCurrencyListBinding
    private val currencyAdapter by lazy { CurrencyAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)

        setDataFromApiToLocalUiModel()

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataToRecyclerView()


    }

    private fun setDataFromApiToLocalUiModel() {
        viewModel.getCurrencies()
    }

    private fun setDataToRecyclerView() {
        viewModel.currencies.observe(viewLifecycleOwner) {
            currencyAdapter.submitList(it)
        }
    }

    private fun initRecyclerView() {
        binding.rVcurrency.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(
                context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

//    private fun getList() {
//        lifecycleScope.launch() {
//            val aboba = viewModel.getCurrencies()
//            val dd = aboba
//        }
//    }
//    private fun getList() {
//        lifecycleScope.launch() {
//            currencyAdapter.setCurrency()
//        }
//    }


}