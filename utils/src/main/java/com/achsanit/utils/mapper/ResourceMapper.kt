package com.achsanit.utils.mapper

import com.achsanit.utils.Resource
import com.achsanit.utils.Statics
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

// function to mapping data from network to resource<T>
suspend inline fun <T> resourceMapper(
    crossinline code: suspend () -> T
): Resource<T> {
    return try {
        // call network success
        // return response after call network as resource success
        Resource.Success(code.invoke())
    } catch (httpException: HttpException) {
        // catch and return error when return http code from network not success
        when (httpException.code()) {
            in 400..499 -> {
                Resource.Error(
                    httpException.message.toString() + "-${httpException.code()}",
                    httpException.code()
                )
            }
            in 500..599 -> {
                // return resource server error when code between 500 and 599
                Resource.ServerError(
                    httpException.message.toString() + "-${httpException.code()}",
                    httpException.code()
                )
            }
            else -> {
                Resource.Error(
                    httpException.message.toString() + "-${httpException.code()}",
                    httpException.code(),
                )
            }
        }
    } catch (e: UnknownHostException) {
        // return resource error when status no internet connection
        e.printStackTrace()
        Resource.Error("Error: No Connection Internet", Statics.NO_INTERNET_CONNECTION)
    } catch (e: SocketTimeoutException) {
        // return resource error when request to long (timeout)
        e.printStackTrace()
        Resource.Error("Error: Request timeout", Statics.REQUEST_TIME_OUT)
    } catch (e: Exception) {
        Resource.Error(e.message.toString(), Statics.SOMETHING_WENT_WRONG)
    }
}