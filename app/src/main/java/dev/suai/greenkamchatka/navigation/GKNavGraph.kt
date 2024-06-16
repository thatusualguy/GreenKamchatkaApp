package dev.suai.greenkamchatka.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.suai.greenkamchatka.TAG
import dev.suai.greenkamchatka.ui.ecomap.EcoMapRoute
import dev.suai.greenkamchatka.ui.menu.MenuRoute
import dev.suai.greenkamchatka.ui.report.send.FileReportRoute
import dev.suai.greenkamchatka.ui.routes.details.RouteDetailsRoute
import dev.suai.greenkamchatka.ui.routes.list.RouteListRoute
import dev.suai.greenkamchatka.ui.visitors.add.CreateVisitorRoute
import dev.suai.greenkamchatka.ui.visitors.add.EditVisitorRoute
import dev.suai.greenkamchatka.ui.visitors.list.VisitorsListRoute
import dev.suai.greenkamchatka.ui.zones.ZonesRoute

@Composable
fun GreenKamchatkaNavGraph(
    navController: NavHostController = rememberNavController(),
    actions: GreenKamchatkaNavigationActions,
    startDestination: String = Destinations.MENU_ROUTE
) {


    NavHost(navController, startDestination) {

        // menu
        composable(route = Destinations.MENU_ROUTE) {
            MenuRoute(
                onZonesClick = actions.navigateToZones,
                onEcomapClick = actions.navigateToEcoMap,
                onPersonsClick = actions.navigateToVisitors,
                onReportClick = actions.navigateToFileReport
            )
        }

        // zones

        composable(
            route = Destinations.ZONES_ROUTE
        ) {
            ZonesRoute(
                onZonePressed = { actions.navigateToRoutes(it) },
                onBackPressed = actions.navigateToMenu
            )
        }


        // visitors

        composable(route = Destinations.PERSONS_ROUTE) {
            VisitorsListRoute(
                onBackPress = actions.navigateToMenu,
                onAddPress = actions.navigateToAddVisitor,
                onEditPress = actions.navigateToEditVisitor
            )
        }

        composable(route = Destinations.PERSONS_ROUTE + "/new") {
            CreateVisitorRoute(
                onBackPress = actions.navigateFromVisitor,
                onSavePress = actions.navigateFromVisitor,
            )
        }

        composable(route = Destinations.PERSONS_ROUTE + "/{id}") {
            val id = it.arguments?.getString("id")?.toIntOrNull() ?: -1
            Log.e(TAG, "EditVisitorRoute: $id ")

            EditVisitorRoute(
                onBackPress = actions.navigateFromVisitor,
                onSavePress = actions.navigateFromVisitor,
                visitorId = id
            )
        }

        // routes

        composable(route = Destinations.ROUTES_ROUTE + "/{id}") {
            val id = it.arguments?.getString("id")?.toIntOrNull() ?: -1

            RouteListRoute(
                zoneId = id,
                onRouteSelected = actions.navigateToRouteDetails,
                onBackPressed = actions.navigateFromVisitor,
            )
        }

        composable(route = Destinations.ROUTE_DETAILS_ROUTE + "/{id}") {
            val id = it.arguments?.getString("id")?.toIntOrNull() ?: -1

            RouteDetailsRoute(
                routeId = id,
                onBackPressed = actions.navigateFromVisitor,
                onRouteBook = { TODO() },
            )
        }


        // permit

        // report

        composable(route = Destinations.FILE_REPORT_ROUTE) {
            FileReportRoute(
                onReportSent = actions.navigateToMenu,
                onBack = actions.navigateToMenu
            )
        }

        // eco map

        composable(route = Destinations.ECOMAP_ROUTE) {
            EcoMapRoute(
                onSendReport = actions.navigateToFileReport,
                onBackPress = { TODO() }
            )
        }
    }

}