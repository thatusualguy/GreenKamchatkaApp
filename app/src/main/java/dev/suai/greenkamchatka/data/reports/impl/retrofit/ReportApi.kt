package dev.suai.greenkamchatka.data.reports.impl.retrofit

// 10 10
data class ReportApi(val type: String, val location:String,  val comment:String, val time:String, val phone:String, val email:String, )

data class ResponseApi(val ID:Int)