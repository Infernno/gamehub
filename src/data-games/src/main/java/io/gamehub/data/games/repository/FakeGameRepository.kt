package io.gamehub.data.games.repository

import arrow.core.Option
import arrow.core.some
import io.gamehub.data.games.models.Game
import kotlinx.coroutines.delay
import javax.inject.Inject

internal class FakeGameRepository @Inject constructor() : GameRepository {
    override suspend fun getPopularGames(): Option<List<Game>> {
        delay(1000)
        return listOf(
            Game(
                "Grand Theft Auto V",
                "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"
            ),
            Game(
                "The Witcher 3: Wild Hunt",
                "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg"
            ),
            Game(
                "Portal 2",
                "https://media.rawg.io/media/games/328/3283617cb7d75d67257fc58339188742.jpg"
            ),
        ).some()
    }

    override suspend fun getAllGames(): Option<List<Game>> {
        return getPopularGames()
    }
}
