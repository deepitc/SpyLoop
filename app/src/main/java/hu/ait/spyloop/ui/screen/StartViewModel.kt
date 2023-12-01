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
    private val _playerNames: MutableList<PlayerName> = mutableListOf()


    fun addPlayer(player: PlayerName) {
        _playerNames.add(player)
        playerNames = _playerNames.toList()
    }

    fun removePlayer(player: PlayerName) {
        _playerNames.remove(player)
        playerNames = _playerNames.toList()
    }
}