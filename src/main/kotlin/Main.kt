import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import kotlin.system.exitProcess

var name = "" ; var server = ""
var socket: Socket? = null
var inputStream: Scanner? = null
var outputStream: PrintWriter? = null

fun main() = runBlocking {
    println("Kotlin Web Chat")

    while (name.isEmpty().or(name.isBlank())) {
        println("enter username:")
        name = readln()
    }

    while (server.isEmpty().or(server.isBlank())) {
        println("enter server IP:")
        server = readln()
    }

    println("connecting to server...")
    try {
        socket = withContext(Dispatchers.IO) {
            Socket(server, 9876)
        }
        inputStream = Scanner(withContext(Dispatchers.IO) {
            socket!!.getInputStream()
        })
        outputStream = PrintWriter(withContext(Dispatchers.IO) {
            socket!!.getOutputStream()
        })
    } catch (e: Exception) {
        e.printStackTrace()
        System.err.println("\nUNABLE TO CONNECT TO SERVER. PRESS ANY KEY TO EXIT")
        readln()
        exitProcess(0)
    }

    outputStream!!.println(name) // send name to server first

    val incoming = async { listenIncoming() }.start()
    val outgoing = async { sendOutgoing()   }.start()
}

suspend fun listenIncoming() { while (true) {
    // TODO: write listener
}}

suspend fun sendOutgoing() { while (true) {
    var dataToSend = readln()
    // TODO: write sender
}}