import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.Socket

var name = "" ; var server = ""

fun main() = runBlocking {
    println("Kotlin Web Chat\ntype your name:")
    name = readln()
    println("type server name:")
    server = readln()

    println("connecting to server...")
    val s: Socket = withContext(Dispatchers.IO) {
        Socket(server, 9876)
    }

    val input  = async { listenIncoming() }
    val output = async { sendOutgoing()   }
}

suspend fun listenIncoming() { while (true) {

}}

suspend fun sendOutgoing() { while (true) {
    var dataToSend = readln()
}}