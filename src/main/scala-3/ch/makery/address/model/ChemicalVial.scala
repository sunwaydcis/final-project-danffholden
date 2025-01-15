package ch.makery.address.model

class ChemicalVial {
  // Define the number of slots a vial has
  private val slotsNum = 4

  // Array to store the colours of the vial slots as integers (0 for empty)
  private var slots: Array[Int] = Array.fill(slotsNum)(0)

  // Function to check if the vial is full
  def isFull(): Boolean = {
    !slots.contains(0)
  }

  // Function to get the number of empty slots in the vial
  def getEmptySlots(): Int = {
    slots.count(_ == 0)
  }

  // Function to get topMost color
  def getTopMostColor(slots: Array[Int]): Int = {
    slots.drop(this.getEmptySlots()).head
  }

  // Function to check if the top colour of this vial matches the top colour of another vial
  private def hasSameTopColour(otherVial: ChemicalVial): Boolean = {
    if (otherVial.getEmptySlots() == 4) {
      true
    }
    else getTopMostColor(slots) == getTopMostColor(otherVial.getSlots())
  }

  // Function to check if a vial can pour colours into another vial
  def canPourInto(otherVial: ChemicalVial): Boolean = {
    hasSameTopColour(otherVial) && !otherVial.isFull()
  }

  // Function to pour colours into another vial
  def pourInto(otherVial: ChemicalVial): (ChemicalVial, ChemicalVial) = {
    if (!this.canPourInto(otherVial)) {
      return (this, otherVial)
    } else {
      val numColoursToPour = math.min(countConsecutiveSameColours(), otherVial.getEmptySlots())

      // Create new slots arrays for both vials
      val newThisVialSlots = (this.slots.drop(numColoursToPour) ++ Array.fill(numColoursToPour)(0)).reverse
      val newOtherVialSlots = Array.fill(numColoursToPour)(this.slots.indexOf(0)) ++ otherVial.getSlots().drop(numColoursToPour)

      // Create new ChemicalVial instances
      val newThisVial = new ChemicalVial()
      newThisVial.setSlots(newThisVialSlots)
      val newOtherVial = new ChemicalVial()
      newOtherVial.setSlots(newOtherVialSlots)

      (newThisVial, newOtherVial)
    }
  }

  // Function to add colours to this vial
  private def addColours(colours: Array[Int]): Unit = {
    var i = 0
    while (i < colours.length && !this.isFull()) {
      slots(i) = colours(i)
      i += 1
    }
  }

  // Function to count the number of consecutive colours that are the same colour as the top colour
  private def countConsecutiveSameColours(): Int = {
    var count = 1
    var i = 1
    while (i < slotsNum && slots(i) == slots.headOption.getOrElse(0)) {
      count += 1
      i += 1
    }
    count
  }



  // Getters and Setters (for testing and potential future use)
  def getSlots(): Array[Int] = slots

  def setSlots(newSlots: Array[Int]): Unit = {
    if (newSlots.length == slotsNum) {
      slots = newSlots
    } else {
      throw new IllegalArgumentException("Invalid number of slots for the vial.")
    }
  }
}