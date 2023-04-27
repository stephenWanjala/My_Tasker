package com.wantech.mytasker.util

sealed class Screen(val route:String){
    object HomeScreen:Screen(route = "Home")

    object AddEditScreen:Screen(route = "add_edit")
}
