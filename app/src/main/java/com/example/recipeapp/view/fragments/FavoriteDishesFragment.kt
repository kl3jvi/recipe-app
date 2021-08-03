package com.example.recipeapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.application.FavDishApplication
import com.example.recipeapp.databinding.FragmentFavoriteDishBinding
import com.example.recipeapp.model.entities.FavDish
import com.example.recipeapp.view.activities.MainActivity
import com.example.recipeapp.view.adapters.FavDishAdapter
import com.example.recipeapp.viewmodel.FavDishViewModel
import com.example.recipeapp.viewmodel.FavDishViewModelFactory

class FavoriteDishesFragment : Fragment() {

    private lateinit var mBinding: FragmentFavoriteDishBinding
    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFavoriteDishBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.rvFavDishesList.layoutManager = GridLayoutManager(requireActivity(), 2)
        val favDishAdapter = FavDishAdapter(this@FavoriteDishesFragment)
        mBinding.rvFavDishesList.adapter = favDishAdapter

        mFavDishViewModel.favoriteDish.observe(viewLifecycleOwner) { dishes ->
            dishes.let {
                if (it.isNotEmpty()) {
                    for (dish in it) {
                        mBinding.rvFavDishesList.visibility = View.VISIBLE
                        mBinding.tvNoFavDishesAddedYet.visibility = View.GONE
                        //it-> is the list with dishes we get from the observer
                        favDishAdapter.dishesList(it)
                    }
                } else {
                    mBinding.rvFavDishesList.visibility = View.GONE
                    mBinding.tvNoFavDishesAddedYet.visibility = View.VISIBLE
                    mBinding.tvNoFavDishesAddedYet.text =
                        getString(R.string.fav_dishes_fragment_label)
                }
            }
        }
    }

    fun dishDetails(favDish: FavDish) {
        findNavController().navigate(FavoriteDishesFragmentDirections.actionFavDishesToDishDetails(
            favDish
        ))
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavBar()
        }
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.showBottomNavBar()
        }
    }
}