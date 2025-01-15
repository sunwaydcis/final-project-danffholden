package ch.makery.address.util

import ch.makery.address.model.ChemicalVial
import scalafx.collections.ObservableBuffer

import scala.util.Random

object GameMethods {

  def startGame(difficulty: String): ObservableBuffer[ChemicalVial] = {
    var vialsList: ObservableBuffer[ChemicalVial] = new ObservableBuffer()
    difficulty match {
      case "Easy" =>    vialsList = scrambleInitVials(2, 1)
      case "Hard" =>    vialsList = scrambleInitVials(6, 2)
      case "Extreme" => vialsList = scrambleInitVials(10, 2)
      case _ => throw new IllegalArgumentException("Invalid difficulty level.")
    }
    vialsList
  }

  def scrambleInitVials(numOfColours: Int, numOfEmpty: Int): ObservableBuffer[ChemicalVial] = {
    val vials = ObservableBuffer.empty[ChemicalVial]

    // Create a list of all possible slots (4 occurrences of each color)
    val allSlots = (1 to numOfColours).flatMap(color => List.fill(4)(color)).toBuffer

    // Shuffle the list of slots randomly
    Random.shuffle(allSlots)

    // Create vials, assigning slots from the shuffled list
    for (_ <- 1 to numOfColours) {
      vials += new ChemicalVial {
        setSlots(Array(allSlots.remove(Random.nextInt(allSlots.length)), allSlots.remove(Random.nextInt(allSlots.length)), allSlots.remove(Random.nextInt(allSlots.length)), allSlots.remove(Random.nextInt(allSlots.length))))
      }
    }

    // Create empty vials
    for (_ <- 1 to numOfEmpty) {
      vials += new ChemicalVial
    }

    vials
  }
}