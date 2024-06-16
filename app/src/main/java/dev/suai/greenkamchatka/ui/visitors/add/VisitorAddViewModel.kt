package dev.suai.greenkamchatka.ui.visitors.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suai.greenkamchatka.data.visitors.Gender
import dev.suai.greenkamchatka.data.visitors.Visitor
import dev.suai.greenkamchatka.data.visitors.VisitorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class VisitorState(val visitor: Visitor = Visitor())

@HiltViewModel(assistedFactory = VisitorAddViewModel.VisitorAddViewModelFactory::class)
class VisitorAddViewModel @AssistedInject constructor(
    @Assisted val userId: Int?,
    private val visitorRepo: VisitorsRepository
) :
    ViewModel() {

    @AssistedFactory
    interface VisitorAddViewModelFactory {
        fun create(id: Int?): VisitorAddViewModel
    }

    private val viewModelState = MutableStateFlow(VisitorState())
    val uiState: StateFlow<VisitorState>
        get() = viewModelState


    init {
        if (userId != null) {
            viewModelScope.launch(Dispatchers.IO) {
                viewModelState.update {
                    it.copy(
                        visitor = visitorRepo.get(userId).first()
                    )
                }
            }
        } else
            viewModelState.update { it.copy(visitor = Visitor()) }
    }

    fun insertVisitor() {
        val visitor = viewModelState.value.visitor

        viewModelScope.launch(Dispatchers.IO) {
            visitorRepo.add(visitor)
            selectVisitor(null)
        }
    }

    fun selectVisitor(visitorId: Int?) {

        if (visitorId == null) viewModelState.update {
            it.copy(visitor = Visitor())
        } else
            viewModelScope.launch(Dispatchers.IO) {
                viewModelState.update {
                    it.copy(
                        visitor = visitorRepo.getAll().last().find { it.id == visitorId }
                            ?: Visitor()
                    )
                }
            }
    }

    fun updateVisitor(newVisitor: Visitor) {
        viewModelScope.launch(Dispatchers.IO) {
            visitorRepo.add(newVisitor)
        }
    }


    fun onFirstNameChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    firstName = a
                )
            )
        }
    }

    fun onLastNameChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    lastName = a
                )
            )
        }
    }

    fun onEmailChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    email = a
                )
            )
        }
    }

    fun onPhoneChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    phone = a
                )
            )
        }
    }

    fun onDobChange(a: Long) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    dob = a
                )
            )
        }
    }

    fun onGenderChange(a: Gender) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    gender = a
                )
            )
        }
    }

    fun onCitizenshipChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    citizenship = a
                )
            )
        }
    }

    fun onRegRegionChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    registrationRegion = a
                )
            )
        }
    }

    fun onPassportSeriesChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    passportSeries = a
                )
            )
        }
    }

    fun onPassportNumChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    passportNum = a
                )
            )
        }
    }

    fun onMiddleNameChange(a: String) {
        viewModelState.update {
            it.copy(
                visitor = it.visitor.copy(
                    middleName = a
                )
            )
        }
    }


}