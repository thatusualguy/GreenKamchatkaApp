package dev.suai.greenkamchatka.data.map

import dev.suai.greenkamchatka.data.GpsPoint

data class ReportMarker(val status:String, val statusId:Int, val typeId:Int, val type:String, val point: GpsPoint)
