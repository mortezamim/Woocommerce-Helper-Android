package ir.mjmim.woocommercehelper.main

import ir.mjmim.woocommercehelper.enums.SigningMethod

class WooBuilder {
    var isHttps: Boolean = false
    var baseUrl: String? = null
    var wc_key: String? = null
    var wc_secret: String? = null
    var signing_method: SigningMethod? = null
    var enableDebug: Boolean = false
}
