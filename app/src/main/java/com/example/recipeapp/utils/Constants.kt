package com.example.recipeapp.utils

object Constants {
    const val DISH_TYPE: String = "DishType"
    const val DISH_CATEGORY: String = "DishCategory"
    const val DISH_COOKING_TIME: String = "DishCookingTime"

    const val DISH_IMAGE_SOURCE_LOCAL: String = "Local"
    const val DISH_IMAGE_SOURCE_ONLINE: String = "Online"
    const val EXTRA_DISH_DETAILS: String = "DishDetails"
    const val ALL_ITEMS: String = "All"
    const val FILTER_SELECTION: String = "FilterSelection"

    const val API_ENDPOINT: String = "recipes/random"
    const val API_KEY: String = "apiKey"
    const val LIMIT_LICENSE: String = "limitLicense"
    const val TAGS: String = "tags"
    const val NUMBER: String = "number"
    const val BASE_URL: String = "https://api.spoonacular.com/"

    const val API_KEY_VALUE: String = "d6e3518068b347aba81e097cfc690697"
    const val LIMIT_LICENSE_VALUE: Boolean = true
    const val TAGS_VALUE: String = "vegetarian,desert"
    const val NUMBER_VALUE: Int = 1

    fun dishTypes(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("Breakfast")
        list.add("Lunch")
        list.add("Snacks")
        list.add("Dinner")
        list.add("Salad")
        list.add("Side Dish")
        list.add("Dessert")
        list.add("Other")
        return list
    }

    fun dishCategories(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("Pizza")
        list.add("BBQ")
        list.add("Bakery")
        list.add("Burger")
        list.add("Cafe")
        list.add("Chicken")
        list.add("Dessert")
        list.add("Drinks")
        list.add("Hot Dogs")
        list.add("Juices")
        list.add("Sandwich")
        list.add("Tea & Coffee")
        list.add("Wraps")
        list.add("Other")
        return list
    }

    fun dishTime(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("5")
        list.add("10")
        list.add("15")
        list.add("20")
        list.add("25")
        list.add("30")
        list.add("45")
        list.add("60")
        list.add("70")
        list.add("80")
        list.add("90")
        list.add("100")
        list.add("110")
        list.add("120")
        return list
    }
}