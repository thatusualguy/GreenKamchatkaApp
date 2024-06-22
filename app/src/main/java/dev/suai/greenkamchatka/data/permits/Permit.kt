package dev.suai.greenkamchatka.data.permits

data class Permit(
    val users: List<PermitUserApi>,
    val visit_date: String,
    val route_id: Int,
    val format_of_visit: String,
    val reason: String,
    val photo: List<String>,
)

data class PermitUserApi(
    val first_name: String,
    val last_name: String,
    val middle_name: String,
    val date_of_birth: String,
    val citizenship: String,
    val region: String,
    val is_male: Boolean,
    val passport: String,
    val email: String,
    val phone: String
)

data class ApiStatus(val success:String)