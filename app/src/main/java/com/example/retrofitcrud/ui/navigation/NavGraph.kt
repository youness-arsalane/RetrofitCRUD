package com.example.retrofitcrud.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.retrofitcrud.ui.screens.DashboardDestination
import com.example.retrofitcrud.ui.screens.DashboardScreen
import com.example.retrofitcrud.ui.screens.users.UserDetailsDestination
import com.example.retrofitcrud.ui.screens.users.UserEditDestination
import com.example.retrofitcrud.ui.screens.users.UserEntryDestination
import com.example.retrofitcrud.ui.screens.users.UserListDestination
import com.example.retrofitcrud.ui.screens.users.DetailsScreen as UserDetailsScreen
import com.example.retrofitcrud.ui.screens.users.EditScreen as UserEditScreen
import com.example.retrofitcrud.ui.screens.users.EntryScreen as UserEntryScreen
import com.example.retrofitcrud.ui.screens.users.ListScreen as UserListScreen

@Composable
fun RetrofitCRUDNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = DashboardDestination.route,
        modifier = modifier
    ) {
        composable(route = DashboardDestination.route) {
            DashboardScreen(
                navController = navController
            )
        }
        composable(route = UserListDestination.route) {
            UserListScreen(
                navigateToUserEntry = {
                    navController.navigate(UserEntryDestination.route)
                },
                navigateToUserUpdate = {
                    navController.navigate("${UserDetailsDestination.route}/${it}")
                },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(route = UserEntryDestination.route) {
            UserEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = UserDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(UserDetailsDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            UserDetailsScreen(
                navigateToEditUser = { navController.navigate("${UserEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = UserEditDestination.routeWithArgs,
            arguments = listOf(navArgument(UserEditDestination.userIdArg) {
                type = NavType.IntType
            })
        ) {
            UserEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
