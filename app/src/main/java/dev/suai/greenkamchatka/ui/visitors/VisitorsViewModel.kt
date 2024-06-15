package dev.suai.greenkamchatka.ui.visitors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suai.greenkamchatka.data.visitors.Gender
import dev.suai.greenkamchatka.data.visitors.Visitor
import dev.suai.greenkamchatka.data.visitors.VisitorsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


data class VisitorsUiState(
    val visitors: List<Visitor> = emptyList(),
    val selectedVisitor: Visitor = Visitor()
)


@HiltViewModel
class VisitorsViewModel @Inject constructor(private val visitorRepo: VisitorsRepository) :
    ViewModel() {

    private val viewModelState = MutableStateFlow(VisitorsUiState())
    val uiState: StateFlow<VisitorsUiState>
        get() = viewModelState

    init {
        refreshVisitors()
    }

    private fun refreshVisitors() {
        viewModelScope.launch(IO) {
            viewModelState.update { it.copy(visitors = visitorRepo.getAll()) }
        }
    }

    fun deleteVisitor(id: Int) {
        val visitor = viewModelState.value.visitors.find { it.id == id } ?: return

        viewModelScope.launch(IO) {
            visitorRepo.delete(visitor)
            viewModelState.update { it.copy(visitors = visitorRepo.getAll()) }
        }
    }

    fun insertVisitor() {
        val visitor = viewModelState.value.selectedVisitor ?: return

        viewModelScope.launch(IO) {
            visitorRepo.add(visitor)
            viewModelState.update { it.copy(visitors = visitorRepo.getAll()) }
            selectVisitor(null)
        }
    }

    fun updateVisitor(newVisitor: Visitor) {
        viewModelScope.launch(IO) {
            visitorRepo.add(newVisitor)
            viewModelState.update { it.copy(visitors = visitorRepo.getAll()) }
        }
    }


    fun onFirstNameChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    firstName = a
                )
            )
        }
    }

    fun onLastNameChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    lastName = a
                )
            )
        }
    }

    fun onEmailChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    email = a
                )
            )
        }
    }

    fun onPhoneChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    phone = a
                )
            )
        }
    }

    fun onDobChange(a: Long) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    dob = a
                )
            )
        }
    }

    fun onGenderChange(a: Gender) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    gender = a
                )
            )
        }
    }

    fun onCitizenshipChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    citizenship = a
                )
            )
        }
    }

    fun onRegRegionChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    registrationRegion = a
                )
            )
        }
    }

    fun onPassportSeriesChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    passportSeries = a
                )
            )
        }
    }

    fun onPassportNumChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    passportNum = a
                )
            )
        }
    }

    fun onMiddleNameChange(a: String) {
        viewModelState.update {
            it.copy(
                selectedVisitor = it.selectedVisitor.copy(
                    middleName = a
                )
            )
        }
    }

    fun selectVisitor(visitorId: Int?) {

        if (visitorId == null) viewModelState.update {
            it.copy(selectedVisitor = Visitor())
        } else viewModelState.update {
            it.copy(
                selectedVisitor = it.visitors.find { it.id == visitorId } ?: Visitor()
            )
        }
    }
}