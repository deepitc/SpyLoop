package hu.ait.spyloop.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.spyloop.data.PlayerName
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(
) : ViewModel() {

    var playerNames by mutableStateOf<List<PlayerName>>(emptyList())
    private val cityNames: MutableList<PlayerName> = mutableListOf()

    fun addPlayer(player: PlayerName) {
        cityNames.add(player)
        playerNames = cityNames.toList()
    }

    fun removePlayer(player: PlayerName) {
        cityNames.remove(player)
        playerNames = cityNames.toList()
    }
}