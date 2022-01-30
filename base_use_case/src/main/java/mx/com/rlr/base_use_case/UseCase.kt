package mx.com.rlr.base_use_case

sealed class UseCase<T> {
    data class Progress<T>(val loading: Boolean, val partialData: T? = null) : UseCase<T>()
    data class Success<T>(val data: T) : UseCase<T>()
    data class Failure<T>(val e: Throwable) : UseCase<T>()

    companion object {
        fun <T> loading(
            isLoading: Boolean = true,
            partialData: T? = null
        ): UseCase<T> = Progress(isLoading, partialData)

        fun <T> success(
            data: T
        ): UseCase<T> = Success(data)

        fun <T> failure(
            e: Throwable
        ): UseCase<T> = Failure(e)
    }
}

fun <T, H> UseCase<T>.mapData(block: (input: T) -> H): UseCase<H> {
    return when (this) {
        is UseCase.Success -> UseCase.success(block.invoke(data))
        is UseCase.Failure -> UseCase.failure(e)
        is UseCase.Progress -> UseCase.loading(loading, partialData?.let { block.invoke(it) })
    }
}