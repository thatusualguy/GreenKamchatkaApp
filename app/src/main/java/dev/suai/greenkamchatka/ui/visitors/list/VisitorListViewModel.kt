package dev.suai.greenkamchatka.ui.visitors.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.suai.greenkamchatka.data.visitors.Visitor
import dev.suai.greenkamchatka.data.visitors.VisitorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class VisitorsListState(
    val visitors: List<Visitor> = emptyList(),
)

@HiltViewModel
class VisitorListViewModel @Inject constructor(private val visitorRepo: VisitorsRepository) :
    ViewModel() {
    private val viewModelState = MutableStateFlow(VisitorsListState())
    val uiState: StateFlow<VisitorsListState>
        get() = viewModelState

    init {
        viewModelScope.launch {
            visitorRepo.getAll().collect { visitors ->
                viewModelState.update { it.copy(visitors = visitors) }
            }
        }
    }

    fun deleteVisitor(id: Int) {
        val visitor = viewModelState.value.visitors.find { it.id == id } ?: return

        viewModelScope.launch(Dispatchers.IO) {
            visitorRepo.delete(visitor)
        }
    }
}