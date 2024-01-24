package com.baltajmn.gigi.domain

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.model.Details

interface GetDetailsUseCase {
    suspend operator fun invoke(id: String): Details
}

class GetDetails(
    private val gigiRepository: GigiRepository
) : GetDetailsUseCase {

    override suspend fun invoke(id: String): Details {
        return gigiRepository.getDetails(id = id)
    }

}