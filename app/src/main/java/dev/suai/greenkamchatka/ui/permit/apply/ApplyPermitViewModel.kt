package dev.suai.greenkamchatka.ui.permit.apply

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suai.greenkamchatka.data.permits.Permit
import dev.suai.greenkamchatka.data.permits.PermitUserApi
import dev.suai.greenkamchatka.data.permits.PermitsRepo
import dev.suai.greenkamchatka.data.visitors.Gender
import dev.suai.greenkamchatka.data.visitors.Visitor
import dev.suai.greenkamchatka.data.visitors.VisitorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.joda.time.DateTime


data class ApplyPermitState(
    val visitor: Visitor,
    val savedVisitors: List<Visitor> = emptyList(),
    val selectedVisitor: Int? = null,
    val date: DateTime,
    val routeId: Int,
    val formatOfVisit: String,
    val reason: String,
    val photo: List<String>,
)

@HiltViewModel(assistedFactory = ApplyPermitViewModel.ApplyPermitViewModelFactory::class)
class ApplyPermitViewModel @AssistedInject constructor(
    @Assisted val routeId: Int,
    private val permitsRepo: PermitsRepo,
    private val visitorsRepository: VisitorsRepository
) : ViewModel() {

    @AssistedFactory
    interface ApplyPermitViewModelFactory {
        fun create(id: Int): ApplyPermitViewModel
    }


    private val viewModelState = MutableStateFlow(
        ApplyPermitState(
            visitor = Visitor(),
            date = DateTime.now(),
            routeId = routeId,
            formatOfVisit = "",
            reason = "",
            photo = emptyList()
        )
    )
    val uiState: StateFlow<ApplyPermitState>
        get() = viewModelState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelState.update {
                it.copy(
                    savedVisitors = visitorsRepository.getAll().first()
                )
            }
        }
    }

    fun onSavedSelect(visitorId: Int?) {
        viewModelState.update {
            it.copy(
                selectedVisitor = visitorId,
            )
        }
    }

    fun onApplyForPermit() {
        var visitor: PermitUserApi

        viewModelScope.launch(Dispatchers.IO) {

            viewModelState.value.let { state ->
                visitor =
                    (if (state.selectedVisitor != null) state.savedVisitors.find { x -> x.id == state.selectedVisitor }
                        ?: state.visitor else state.visitor).let { visitor ->
                        PermitUserApi(first_name = visitor.firstName,
                            last_name = visitor.lastName,
                            middle_name = visitor.middleName,
                            phone = visitor.phone,
                            email = visitor.email,
                            citizenship = visitor.citizenship,
                            region = visitor.registrationRegion,
                            is_male = visitor.gender == Gender.Male,
                            passport = visitor.passportSeries + " " + visitor.passportNum,
                            date_of_birth = visitor.dob.let { DateTime(it).toString("yyyy-MM-dd") })
                    }


                val p = Permit(
                    listOf(visitor),
                    visit_date = state.date.toString("yyyy-MM-dd"),
                    photo = emptyList(),
                    reason = state.reason,
                    route_id = state.routeId,
                    format_of_visit = "Многодневный тур(от 1 ночевки и более)"
                )



                permitsRepo.sendPermit(permit = p)
            }
        }
    }

    fun changeVisitor(visitor: Visitor) {
        viewModelState.update {
            it.copy(
                visitor = visitor
            )
        }
    }

    fun onReasonChange(reason: String) {
        viewModelState.update {
            it.copy(
                reason = reason
            )
        }
    }

    fun onDateChange(date: Long) {
        viewModelState.update {
            it.copy(
                date = DateTime(date)
            )
        }
    }
}