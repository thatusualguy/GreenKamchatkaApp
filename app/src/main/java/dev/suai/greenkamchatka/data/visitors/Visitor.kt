package dev.suai.greenkamchatka.data.visitors

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity
data class Visitor(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val firstName: String = "",
    val lastName: String = "",
    val middleName: String = "",
    val phone: String = "",
    val email: String = "",
    val passportSeries: String = "",
    val passportNum: String = "",
    val citizenship: String = "",
    val registrationRegion: String = "",
    val gender: Gender = Gender.Female,
    val dob: Long = Instant.now().epochSecond
)


enum class Gender {
    Male,
    Female
}