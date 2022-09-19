package dojo.httpcall

import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.CompletableFuture

fun call(url: String) {
    val url = URL(url)
    val con: HttpURLConnection = url.openConnection() as HttpURLConnection
    con.requestMethod = "GET"
    con.readTimeout = 1000
    con.connectTimeout = 1000
    val responseCode = con.responseCode
}

tailrec fun recurCall(url: String, count: Long = 0) {
    try {
        call(url)
    } catch (_: Exception) {
    }
    Thread.sleep(5L)
    println(count)
    recurCall(url, count + 1)
}

fun main() {
    CompletableFuture.runAsync {
        recurCall("http://127.0.0.1:17070/v1/fulfillment/order/hello")
    }
    CompletableFuture.runAsync {
        recurCall("http://127.0.0.1:17070/v1/fulfillment/order/hello")
    }
    recurCall("http://127.0.0.1:17070/v1/fulfillment/order/hello")
}
