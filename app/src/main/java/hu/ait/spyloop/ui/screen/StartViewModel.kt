package hu.ait.spyloop.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.spyloop.data.Player
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
) : ViewModel() {

    companion object {
        var _players = mutableStateListOf<Player>()

        fun clearAllPlayers() {
            _players.clear()
        }

        fun resetOutOfLooper() {
            for (player in _players) {
                player.outOfLoop = false
            }
        }
    }


    fun getAllPlayers(): List<Player> {
        return _players
    }

    fun addPlayer(player: Player) {
        _players.add(player)
    }

    fun pickOutOfLoop(players: List<Player>) {
        val outOfLoop = players.random()
        outOfLoop.outOfLoop = true
    }

    fun removePlayer(player: Player) {
        _players.remove(player)
    }
}