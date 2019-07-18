package ir.mjmim.woocommercehelper.enums

enum class RequestMethod private constructor(s: String) {
    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), HEAD("HEAD");

    var `val`: String
        internal set

    init {
        `val` = s
    }
}
