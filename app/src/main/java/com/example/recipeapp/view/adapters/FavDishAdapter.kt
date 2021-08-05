package com.example.recipeapp.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemDishLayoutBinding
import com.example.recipeapp.model.entities.FavDish
import com.example.recipeapp.utils.Constants
import com.example.recipeapp.view.activities.AddUpdateRecipeActivity
import com.example.recipeapp.view.fragments.AllRecipesFragment
import com.example.recipeapp.view.fragments.FavoriteDishesFragment

class FavDishAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<FavDishAdapter.ViewHolder>() {

    private var dishes: List<FavDish> = listOf()


    class ViewHolder(view: ItemDishLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        val ivDishImage = view.ivDishImage
        val tvTitle = view.tvDishTitle
        val ibMore = view.ibMore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDishLayoutBinding =
            ItemDishLayoutBinding.inflate(
                LayoutInflater.from((fragment.context)), parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes[position]
        Glide.with(fragment)
            .load(dish.image)
            .into(holder.ivDishImage)
        holder.tvTitle.text = dish.title

        holder.itemView.setOnClickListener {
            if (fragment is AllRecipesFragment) {
                fragment.dishDetails(dish)
            }
            if (fragment is FavoriteDishesFragment) {
                fragment.dishDetails(dish)
            }
        }

        holder.ibMore.setOnClickListener {
            val popupMenu = PopupMenu(fragment.context, holder.ibMore)
            popupMenu.menuInflater.inflate(R.menu.menu_adapter, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                if (it.itemId == R.id.action_edit_dish) {

                    val intent =
                        Intent(fragment.requireActivity(), AddUpdateRecipeActivity::class.java)
                    intent.putExtra(Constants.EXTRA_DISH_DETAILS, dish)
                    fragment.requireActivity().startActivity(intent)


                } else if (it.itemId == R.id.action_delete_dish) {
                    if (fragment is AllRecipesFragment) {
                        fragment.deleteDish(dish)
                    }


                }
                true
            }
            popupMenu.show()
        }


        if (fragment is AllRecipesFragment) {
            holder.ibMore.visibility = View.VISIBLE
        } else if (fragment is FavoriteDishesFragment) {
            holder.ibMore.visibility = View.INVISIBLE
        }


    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    fun dishesList(list: List<FavDish>) {
        dishes = list
        notifyDataSetChanged()
    }

}