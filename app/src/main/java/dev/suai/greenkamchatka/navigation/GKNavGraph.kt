package dev.suai.greenkamchatka.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.suai.greenkamchatka.ui.menu.MenuRoute
import dev.suai.greenkamchatka.ui.routes.details.RouteDetailsRoute
import dev.suai.greenkamchatka.ui.routes.list.RouteListRoute
import dev.suai.greenkamchatka.ui.visitors.add.AddVisitorRoute
import dev.suai.greenkamchatka.ui.visitors.list.VisitorsListRoute
import dev.suai.greenkamchatka.ui.zones.ZonesRoute

@Composable
fun GreenKamchatkaNavGraph(
    navController: NavHostController = rememberNavController(),
    actions: GreenKamchatkaNavigationActions,
    startDestination: String = Destinations.MENU_ROUTE
) {


    NavHost(navController, startDestination) {

        composable(route = Destinations.MENU_ROUTE) {
            MenuRoute(
                onZonesClick =  actions.navigateToZones,
                onEcomapClick = { TODO() },
                onPersonsClick =  actions.navigateToVisitors,
                onReportClick = { TODO() }
            )
        }

        composable(
            route = Destinations.ZONES_ROUTE
        ) {
            ZonesRoute(
                onZonePressed = { actions.navigateToRoutes(it) },
                onBackPressed = actions.navigateToMenu
            )
        }

        composable(route = Destinations.PERSONS_ROUTE) {
            VisitorsListRoute(
                onBackPress = actions.navigateToMenu,
                onAddPress = actions.navigateToAddVisitor,
                onEditPress = actions.navigateToEditVisitor
            )
        }

        composable(route = Destinations.PERSONS_ROUTE + "/new") {
            AddVisitorRoute(
                onBackPress = actions.navigateFromVisitor,
                onSavePress = actions.navigateFromVisitor,
                visitorId = null
            )
        }

        composable(route = Destinations.PERSONS_ROUTE + "?{id}") {
            val id = it.arguments?.getString("id")
            AddVisitorRoute(
                onBackPress = actions.navigateFromVisitor,
                onSavePress = actions.navigateFromVisitor,
                visitorId = id?.toInt()
            )
        }

        composable(route = Destinations.ROUTES_ROUTE + "?{id}") {
            val id = it.arguments?.getString("id")?.toIntOrNull() ?: -1

            RouteListRoute(
                zoneId = id,
                onRouteSelected = actions.navigateToRouteDetails,
                onBackPressed = actions.navigateFromVisitor,
            )
        }

        composable(route = Destinations.ROUTE_DETAILS_ROUTE + "?{id}") {
            val id = it.arguments?.getString("id")?.toIntOrNull() ?: -1

            RouteDetailsRoute(
                routeId = id,
                onRouteBook = actions.navigateToRouteDetails,
                onBackPressed = actions.navigateFromVisitor,
            )
        }
    }

}