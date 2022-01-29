package mx.com.rlr.http_client.data

enum class HttpErrorCode(val code: Int) {
    /** 400 */
    BAD_REQUEST(400),
    /** 401 */
    UNAUTHORIZED(401),
    /** 402 */
    PAYMENT_REQUIRED(402),
    /** 403 */
    FORBIDDEN(403),
    /** 404 */
    NOT_FOUND(404),
    /** 405 */
    METHOD_NOT_ALLOWED(405),
    /** 406 */
    NOT_ACCEPTABLE(406),
    /** 408 */
    REQUEST_TIMEOUT(408),
    /** 409 */
    CONFLICT(409),
    /** 410 */
    GONE(410),
    /** 411 */
    LENGTH_REQUIRED(411),
    /** 409 */
    PRECONDITION_FAILED(412),
    /** 422 */
    UNPROCESSABLE_ENTITY(422),
    /** 500 */
    SERVER_ERROR(500);

    companion object {

        fun from(code: Int): HttpErrorCode = when (code) {
            400 -> BAD_REQUEST
            401 -> UNAUTHORIZED
            402 -> PAYMENT_REQUIRED
            403 -> FORBIDDEN
            404 -> NOT_FOUND
            405 -> METHOD_NOT_ALLOWED
            406 -> NOT_ACCEPTABLE
            408 -> REQUEST_TIMEOUT
            409 -> CONFLICT
            411 -> LENGTH_REQUIRED
            412 -> PRECONDITION_FAILED
            422 -> UNPROCESSABLE_ENTITY
            else -> SERVER_ERROR
        }

    }

}