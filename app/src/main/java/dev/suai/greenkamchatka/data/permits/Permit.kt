package dev.suai.greenkamchatka.data.permits

import android.app.Person
import dev.suai.greenkamchatka.data.visitors.Visitor

data class Permit(
    val person: List<Visitor>,
    val date: String,
    val route_id: Int,
    val format_of_visit: String,
    val reason: String,
    val photo: List<String>,
)