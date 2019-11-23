package de.spellmaker.mbrenner.cli

import de.spellmaker.mbrenner.Interval
import de.spellmaker.mbrenner.impl.mergeInMemory

/**
 * Example main including a command line tool
 * @param args program arguments, ignored
 */
fun main(args : Array<String>) {
    while (true) {
        println("Please input a number of intervals in the form [1, 2], [3, 4], ... or type 'c' to abort")
        val input = readLine()
        if (input == null) {
            println("Invalid or no input detected");
            continue
        }
        if (input.trim().startsWith("c")) {
            break
        }
        val intervals : List<Interval>
        try {
             intervals = parseInput(input)
        } catch (e : Exception) {
            println("Invalid or no input detected");
            continue
        }

        try {
            println("The merged result is '${mergeInMemory(intervals)}'")
        } catch(e : Exception) {
            println("An unexpected error occurred during the merge application: $e")
            continue;
        }
    }

    println("goodbye")
}