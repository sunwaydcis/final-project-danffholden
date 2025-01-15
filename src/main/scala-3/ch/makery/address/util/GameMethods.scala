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
    // Create vials with random colours
    for (_ <- 1 to numOfColours) {
      vials += new ChemicalVial {
        setSlots(Array.fill(4)(Random.nextInt(numOfColours) + 1)) // Assign random colours (1 to numOfColours)
      }
    }

    // Create empty vials
    for (_ <- 1 to numOfEmpty) {
      vials += new ChemicalVial
    }

    // Shuffle the vials
    Random.shuffle(vials)
    vials
  }
  
  
}