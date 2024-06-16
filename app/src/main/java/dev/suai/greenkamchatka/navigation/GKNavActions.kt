package dev.suai.greenkamchatka.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class GreenKamchatkaNavigationActions(navController: NavController) {

    // menu

    val navigateToMenu: () -> Unit = {
        navController.navigate(Destinations.MENU_ROUTE) {
            launchSingleTop = true
        }
    }


    // visitors

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

    val navigateToEditVisitor: (Int) -> Unit = {
        navController.navigate(Destinations.PERSONS_ROUTE + "/" + it.toString()) {
            launchSingleTop = true
        }
    }

    val navigateFromVisitor: () -> Unit = {
        navController.navigate(Destinations.PERSONS_ROUTE) {
            popUpTo(Destinations.PERSONS_ROUTE)
            launchSingleTop = true
        }
    }


    // zone

    val navigateToZones: () -> Unit = {

        navController.navigate(Destinations.ZONES_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = false
            }

            launchSingleTop = true
//            restoreState = true
        }
    }

    // routes

    val navigateToRoutes: (Int) -> Unit = {
        navController.navigate(Destinations.ROUTES_ROUTE + "/" + it.toString())
        {
            launchSingleTop = true
            restoreState = true
        }
    }


    val navigateToRouteDetails: (Int) -> Unit = {
        navController.navigate(Destinations.ROUTE_DETAILS_ROUTE + "/" + it.toString())
        {

            launchSingleTop = true
            restoreState = true
        }
    }


    // eco map

    val navigateToEcoMap: () -> Unit = {
        navController.navigate(Destinations.ECOMAP_ROUTE) {
            launchSingleTop = true
        }
    }

    // report

    val navigateToFileReport: () -> Unit = {
        navController.navigate(Destinations.FILE_REPORT_ROUTE) {
            launchSingleTop = true
        }
    }

    val navigateToUnsentReports: () -> Unit = {
        navController.navigate(Destinations.UNSENT_REPORTS_ROUTE) {
            launchSingleTop = true

        }
    }

    // permit

    val navigateToPermitType: (Int) -> Unit = {
        navController.navigate(Destinations.APPLY_PERMIT_TYPE_ROUTE + "/$it") {
            launchSingleTop = true
        }
    }

    val navigateToPermit: (Int) -> Unit = {
        navController.navigate(Destinations.APPLY_PERMIT_ROUTE + "/$it") {
            launchSingleTop = true
        }
    }
}