package com.example.moviesapi.data.mapper

import com.example.moviesapi.data.database.MovieDbModel
import com.example.moviesapi.data.network.models.MovieDto
import com.example.moviesapi.domain.model.Movie

class MovieMapper {

    fun mapDbModelToEntity(dbModel: MovieDbModel) = Movie(
        id  = dbModel.id,
        name = dbModel.name,
        description = dbModel.description,
        imageUrl = dbModel.imageUrl,
    )

    fun mapDtoToDbModel(dto: MovieDto) = MovieDbModel(
        name = dto.name,
        description = dto.description,
        imageUrl = dto.multimedia?.imageUrl.toString(),
    )
}