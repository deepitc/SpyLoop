package hu.ait.spyloop.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.spyloop.data.Player
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class StartViewModel @Inject constructor(
) : ViewModel() {
    val categoriesList: List<String> = listOf("Locations", "Food", "Household Items")


    companion object {
        var _players = mutableStateListOf<Player>()
    }

    fun getAllCategories(): List<String>{
        return categoriesList
    }

    fun getAllPlayers(): List<Player> {
        return _players
    }

    fun addPlayer(player: Player) {
        _players.add(player)
    }

    fun pickOutOfLoop(players: List<Player>): Player {
        val outOfLoop = players.random()
        outOfLoop.outOfLoop = true
        return outOfLoop
    }

    fun removePlayer(player: Player) {
        _players.remove(player)
    }
}