package dev.suai.greenkamchatka.data.routes.impl

data class RouteApi(
    val routes: List<RouteApiMember>
//    val tags: List<String>
)

data class RouteApiMember(val id: Int,
                          val zone_id: Int,
                          val name: String,
                          val duration: Int,
                          val desc: String,
                          val img_urls: List<String>,)