package mx.com.rlr.core.failureHandler

val Exception.tag: String get() = javaClass.simpleName

fun Exception.message(): String = message ?: tag