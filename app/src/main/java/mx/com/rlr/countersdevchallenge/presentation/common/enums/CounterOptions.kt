package mx.com.rlr.countersdevchallenge.presentation.common.enums

enum class CounterOptions {
    ADD,
    DELETE,
    INCREASE,
    DECREASE,
    UNKNOWN;

    override fun toString(): String = when(this) {
        ADD -> "Add"
        DELETE -> "Delete"
        INCREASE -> "Increase"
        DECREASE -> "Decrease"
        UNKNOWN -> "Unknown"
    }

    companion object {

        fun toCounterOptions(value: String) = when(value) {
            "Add" -> ADD
            "Delete" -> DELETE
            "Increase" -> INCREASE
            "Decrease" -> DECREASE
            else -> UNKNOWN
        }

    }

}