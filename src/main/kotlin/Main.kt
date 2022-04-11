import kotlinx.coroutines.*
import java.io.PrintWriter
import java.net.Socket
import java.util.*
import kotlin.system.exitProcess

var name = "" ; var server = ""
var socket: Socket? = null
var inputStream: Scanner? = null
var outputStream: PrintWriter? = null

fun main() {
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

    val incoming = listenIncoming().start()
    val outgoing = sendOutgoing().start()

}

fun listenIncoming() = CoroutineScope(Dispatchers.IO).async{while(true) {
    // TODO: write listener
}}

fun sendOutgoing() = CoroutineScope(Dispatchers.IO).async{while(true) {
    var dataToSend = readln()
    // TODO: write sender
}}