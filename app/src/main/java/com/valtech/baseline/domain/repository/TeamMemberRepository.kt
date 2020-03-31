package com.valtech.baseline.domain.repository

import com.valtech.baseline.core.functional.Result
import com.valtech.baseline.core.functional.wrapResult
import com.valtech.baseline.data.datasource.local.LocalTeamMemberRepository
import com.valtech.baseline.data.datasource.remote.RemoteTeamMembersRepository
import com.valtech.baseline.domain.error.Error
import com.valtech.baseline.domain.error.NoNetworkConnectionException
import com.valtech.baseline.domain.model.Member
import java.net.UnknownHostException

class TeamMemberRepository(
    private val localRepository: LocalTeamMemberRepository,
    private val remoteRepository: RemoteTeamMembersRepository
) {
    suspend fun getAllTeamMembers(): Result<List<Member>, Error> {
        return wrapResult {
            remoteRepository.getTeamMembers().also {
                localRepository.storeMembers(it)
            }
        }
    }

    fun storeAllTeamMembers(members: List<Member>): Result<Any, Error> {
        return wrapResult {
            return@wrapResult localRepository.storeMembers(members)
        }
    }

    private fun mapUserExceptionToError(exception: Exception): Error {
        return when (exception) {
            is NoNetworkConnectionException -> Error.MissingNetworkConnection
            is UnknownHostException -> Error.MissingNetworkConnection
            else -> Error.GeneralError(exception)
        }
    }
}
