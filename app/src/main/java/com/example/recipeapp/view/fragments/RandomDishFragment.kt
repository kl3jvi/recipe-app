package com.example.recipeapp.view.fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.application.FavDishApplication
import com.example.recipeapp.databinding.FragmentRandomDishBinding
import com.example.recipeapp.model.entities.FavDish
import com.example.recipeapp.model.entities.RandomDish
import com.example.recipeapp.utils.Constants
import com.example.recipeapp.viewmodel.FavDishViewModel
import com.example.recipeapp.viewmodel.FavDishViewModelFactory
import com.example.recipeapp.viewmodel.RandomDishViewModel

class RandomDishFragment : Fragment() {

    private var mBinding: FragmentRandomDishBinding? = null
    private lateinit var mRandomDishViewModel: RandomDishViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentRandomDishBinding.inflate(inflater, container, false)


        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRandomDishViewModel = ViewModelProvider(this).get(RandomDishViewModel::class.java)

        mRandomDishViewModel.getRandomRecipeFromAPI()
        randomDishViewModelObserver()
    }

    //this is an observer
    private fun randomDishViewModelObserver() {
        mRandomDishViewModel.randomDishResponse.observe(viewLifecycleOwner, { randomDishResponse ->
            randomDishResponse?.let {
                Log.i("Random Dishes Response", "${randomDishResponse.recipes[0]}")
                setRandomResponesOnUI(randomDishResponse.recipes[0])
            }
        })

        mRandomDishViewModel.randomDishLoadingError.observe(viewLifecycleOwner, { dataError ->
            dataError?.let {
                Log.e("Error", dataError.toString())
            }
        })

        mRandomDishViewModel.loadRandomDish.observe(viewLifecycleOwner, { loadRandomDish ->
            loadRandomDish?.let {
                Log.i("Random dish", "$loadRandomDish")
            }
        })
    }

    private fun setRandomResponesOnUI(recipe: RandomDish.Recipe) {
        Glide.with(requireActivity())
            .load(recipe.image)
            .centerCrop()
            .into(mBinding!!.ivDishImage)

        mBinding!!.dtTitle.text = recipe.title
        var dishType = "other"
        if (recipe.dishTypes.isNotEmpty()) {
            dishType = recipe.dishTypes[0]
            mBinding!!.dtType.text = dishType
        }
        mBinding!!.dtCategory.text = "Other"
        var ingredients = ""
        for (value in recipe.extendedIngredients) {
            ingredients = if (ingredients.isEmpty()) {
                value.original
            } else {
                ingredients + ", \n" + value.original
            }
        }
        mBinding!!.dtIngredientsVal.text = ingredients

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding!!.dtDirectionVal.text =
                Html.fromHtml(recipe.instructions, Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            mBinding!!.dtDirectionVal.text = Html.fromHtml(recipe.instructions)
        }

        mBinding!!.dtTime.text =
            resources.getString(R.string.cook_time_format, recipe.readyInMinutes.toString())

        mBinding!!.ivFavoriteDish.setOnClickListener {
            val randomDetails = FavDish(
                recipe.image,
                Constants.DISH_IMAGE_SOURCE_ONLINE,
                recipe.title,
                dishType,
                "Other",
                ingredients,
                recipe.readyInMinutes.toString(),
                recipe.instructions,
                true
            )

            val mFavDishViewModel: FavDishViewModel by viewModels {
                FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
            }
            mFavDishViewModel.insert(randomDetails)
            mBinding!!.ivFavoriteDish.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_favorite_selected
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}