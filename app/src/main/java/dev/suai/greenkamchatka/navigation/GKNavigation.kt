package dev.suai.greenkamchatka.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.suai.greenkamchatka.TAG

class GreenKamchatkaNavigationActions(navController: NavController) {



    val navigateToMenu:()->Unit={
        navController.navigate(Destinations.MENU_ROUTE){
            launchSingleTop = true
        }
    }

    val navigateToVisitors: () -> Unit = {
        navController.navigate(Destinations.PERSONS_ROUTE) {
            launchSingleTop = true
        }
    }

    val navigateToAddVisitor: () -> Unit = {
        navController.navigate(Destinations.PERSONS_ROUTE + "/new") {
            launchSingleTop = true
        }
    }

    val navigateToEditVisitor:(Int)->Unit={
        navController.navigate(Destinations.PERSONS_ROUTE+"?"+it.toString()){
            launchSingleTop = true
        }
    }

    val navigateFromVisitor: () -> Unit = {
        navController.navigate(Destinations.PERSONS_ROUTE) {
            popUpTo(Destinations.PERSONS_ROUTE)
            launchSingleTop = true
        }
    }



    val navigateToZones: () -> Unit = {

        Log.d(TAG, "null() called")

        Log.e(TAG, "to zones" )

        navController.navigate(Destinations.ZONES_ROUTE) {
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = false
//            }

            launchSingleTop = true
//            restoreState = true
        }
    }

    val navigateToRoutes: (Int) -> Unit = {
        navController.navigate(Destinations.ROUTES_ROUTE+"?"+it.toString())
        {
            launchSingleTop = true
            restoreState = true
        }
    }


    val navigateToRouteDetails: (Int) -> Unit = {
        navController.navigate(Destinations.ROUTE_DETAILS_ROUTE+"?"+it.toString())
        {

            launchSingleTop = true
            restoreState = true
        }
    }
}