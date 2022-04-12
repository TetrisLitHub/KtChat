import kotlinx.coroutines.*
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import kotlin.coroutines.suspendCoroutine
import kotlin.system.exitProcess

var name = "" ; var server = ""
var socket: Socket? = null
var inputStream: Scanner? = null
var outputStream: PrintWriter? = null

fun main() {
    println("Kotlin Web Chat, created as a way to implement Coroutines")

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
        socket = Socket(server, 9876)
        inputStream = Scanner(socket!!.getInputStream())
        outputStream = PrintWriter(socket!!.getOutputStream())
    } catch (e: Exception) {
        e.printStackTrace()
        System.err.println("\nUNABLE TO CONNECT TO SERVER. PRESS ANY KEY TO EXIT")
        readln()
        exitProcess(0)
    }

    outputStream!!.println(name) // send name to server first

    runBlocking {
        val incoming = async { listenIncoming() }
        val outgoing = async { sendOutgoing()   }
    }
}

suspend fun listenIncoming() {
    delay(1000)
    println("i")
}

suspend fun sendOutgoing() {
    delay(2)
    println("s")
}