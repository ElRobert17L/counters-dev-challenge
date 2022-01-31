package mx.com.rlr.core.failureHandler

fun <T> tryOrNull(throwableAction: () -> T): T? =
    try {
        throwableAction()
    } catch (e: Exception) {
        null
    }