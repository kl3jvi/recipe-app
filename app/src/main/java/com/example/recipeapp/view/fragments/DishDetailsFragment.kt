package com.example.recipeapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentDishDetailsBinding
import java.io.IOException

class DishDetailsFragment : Fragment() {

    private var mBinding: FragmentDishDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            FragmentDishDetailsBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DishDetailsFragmentArgs by navArgs()

        args.let {
            try {
                Glide.with(requireActivity())
                    .load(it.dishDetails.image)
                    .centerCrop()
                    .into(mBinding!!.ivDishImage)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            mBinding!!.dtTitle.text = it.dishDetails.title
            mBinding!!.dtType.text = it.dishDetails.type
            mBinding!!.dtCategory.text = it.dishDetails.category
            mBinding!!.dtIngredientsVal.text = it.dishDetails.ingredients
            mBinding!!.dtDirectionVal.text = it.dishDetails.cookingDirection
            mBinding!!.dtTime.text =
                resources.getString(R.string.cook_time_format, it.dishDetails.cookingTime)

        }
    }
}