package com.example.up.common

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.EOFException
import kotlinx.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> safeApiCall(call: suspend () -> T): Flow<Resource<T>> = flow {
    emit(Resource.Loading())
    try {
        emit(Resource.Success(call()))
    } catch (e: UnknownHostException) {
        emit(Resource.Error("Нет соединения с интернетом"))
    } catch (e: ConnectException) {
        emit(Resource.Error("Сервер недоступен"))
    } catch (e: ConnectTimeoutException) {
        emit(Resource.Error("Не удалось подключиться к серверу"))
    } catch (e: SocketTimeoutException) {
        emit(Resource.Error("Превышено время ожидания ответа"))
    } catch (e: HttpRequestTimeoutException) {
        emit(Resource.Error("Сервер не отвечает (таймаут)"))
    } catch (e: ClientRequestException) {
        emit(Resource.Error("Ошибка запроса: ${e.response.status.value}"))
    } catch (e: ServerResponseException) {
        emit(Resource.Error("Ошибка сервера: ${e.response.status.value}"))
    } catch (e: ResponseException) {
        emit(Resource.Error(e.message ?: "Ошибка ответа сервера"))
    } catch (e: EOFException) {
        emit(Resource.Error("Сервер закрыл соединение: ${e.message}"))
    } catch (e: IOException) {
        emit(Resource.Error("Проблема с сетью"))
    } catch (e: Exception) {
        emit(Resource.Error(e.message ?: "Неизвестная ошибка"))
    }
}