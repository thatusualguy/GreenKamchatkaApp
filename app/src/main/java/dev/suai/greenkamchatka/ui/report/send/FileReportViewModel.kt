package dev.suai.greenkamchatka.ui.report.send

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suai.greenkamchatka.data.GpsPoint
import dev.suai.greenkamchatka.data.reports.Report
import dev.suai.greenkamchatka.data.reports.ReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject


data class FileReportState(
    val type: Int = 1,
    val imageUri: Uri? = null,
    val email: String = "",
    val phone: String = "",
    val comment: String = "",
)

@HiltViewModel
class FileReportViewModel @Inject constructor(private val reportRepo: ReportRepository) :
    ViewModel() {

    private val viewModelState = MutableStateFlow(FileReportState())
    val uiState: StateFlow<FileReportState>
        get() = viewModelState

    fun fileReport() {
        viewModelScope.launch(Dispatchers.IO) {
            reportRepo.sendReport(
                viewModelState.value.let {
                    Report(
                        email = it.email,
                        type = it.type,
                        imageUri = it.imageUri,
                        phone = it.phone,
                        comment = it.comment,
                        time = Instant.now().epochSecond,
                        location = GpsPoint(0.0, 0.0)
                    )
                }
            )
        }
    }


    fun onTypeChange(type: Int) {
        viewModelState.update {
            it.copy(
                type = type
            )
        }
    }

    fun onEmailChange(email: String) {
        viewModelState.update {
            it.copy(
                email = email
            )
        }
    }

    fun onPhoneChange(phone: String) {
        viewModelState.update {
            it.copy(
                phone = phone
            )
        }
    }

    fun onCommentChange(comment: String) {
        viewModelState.update {
            it.copy(
                comment = comment
            )
        }
    }

    fun onImageChanged(imageUri: Uri?) {
        viewModelState.update {
            it.copy(
                imageUri = imageUri
            )
        }
    }
}
