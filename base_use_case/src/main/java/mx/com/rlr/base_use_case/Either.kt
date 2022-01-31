package mx.com.rlr.base_use_case

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {

    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val left: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val right: R) : Either<Nothing, R>()

    /**
     * Returns true if this is a Right, false otherwise.
     * @see Right
     */
    val isRight get() = this is Right<R>

    /**
     * Returns true if this is a Left, false otherwise.
     * @see Left
     */
    val isLeft get() = this is Left<L>

    /**
     * Creates a Left type.
     * @see Left
     */
    fun <L> left(a: L) = Left(a)

    /**
     * Creates a Left type.
     * @see Right
     */
    fun <R> right(b: R) = Right(b)

    /**
     * Obtains the right value if Either is Right.
     * @throws Exception if Either is Left.
     */
    fun rightValue() : R =
        if(this is Right)
            this.right
        else
            throw Exception("Either value its not Right")

    /**
     * Obtains the left value if Either is Left.
     * @throws Exception if Either is Right.
     */
    fun leftValue() : L =
        if(this is Left)
            this.left
        else
            throw Exception("Either value its not Left")

}

/**
 * Right-biased flatMap() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(left)
        is Either.Right -> fn(right)
    }

/**
 * Right-biased flatMap() FP convention which means that Right is assumed to be the default case
 * to operate on. If it is Left, operations like map, flatMap, ... return the Left value unchanged.
 */
suspend fun <T, L, R> Either<L, R>.flatMapSuspend(fn: suspend (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(left)
        is Either.Right -> fn(right)
    }

/**
 * Executes the received as param function if the Either value is Left. Do nothing in other case.
 * Returns the current Either value after these.
 */
suspend fun <L, R> Either<L, R>.onLeft(fn: suspend (L) -> Unit) : Either<L, R> =
    when (this){
        is Either.Left -> fn(left)
        is Either.Right -> { /* PASS */ }
    }.let { this }

/**
 * Executes the received as param function if the Either value is Right. Do nothing in other case.
 * Returns the current Either value after these.
 */
suspend fun <L, R> Either<L, R>.onRight(fn: suspend (R) -> Unit): Either<L, R> =
    when (this) {
        is Either.Left -> { /* PASS */ }
        is Either.Right -> fn(right)
    }.let { this }

/**
 * Returns Right if the current Either it is it. Obtains an Left value composed making use of
 * the function received as param.
 */
fun <L, R, T> Either<L, R>.composeFailure(fn: (L) -> T) : Either<T, R> =
    when (this){
        is Either.Left ->
            Either.Left(fn(left))
        is Either.Right ->
            Either.Right(right)
    }