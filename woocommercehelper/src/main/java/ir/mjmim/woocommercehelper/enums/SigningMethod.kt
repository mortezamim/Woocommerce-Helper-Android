package ir.mjmim.woocommercehelper.enums

enum class SigningMethod private constructor(s: String) {
    HMACSHA1("HMAC-SHA1"), HMACSHA256("HMAC-SHA256");

    var `val`: String
        internal set

    init {
        `val` = s
    }
}
