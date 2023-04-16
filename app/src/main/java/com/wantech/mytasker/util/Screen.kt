package com.wantech.mytasker.util

sealed class Screen(val route:String){
    object HomeScreen:Screen(route = "Home")
}
