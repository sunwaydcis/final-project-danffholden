package ch.makery.address.model

class ChemicalVial {
  // Define the number of slots a vial has
  private val slotsNum = 4

  // Array to store the colours of the vial slots as integers (0 for empty)
  private var slots: Array[Int] = Array.fill(slotsNum)(0)

  // Function to check if the vial is full
  def isFull(): Boolean = {
    slots.forall(_ != 0)
  }

  // Function to check if a vial can pour colours into another vial
  def canPourInto(otherVial: ChemicalVial): Boolean = {
    !isFull() && hasSameTopColour(otherVial) && !otherVial.isFull()
  }

  // Function to check if the top colour of this vial matches the top colour of another vial
  private def hasSameTopColour(otherVial: ChemicalVial): Boolean = {
    slots.headOption.getOrElse(0) == otherVial.getTopColour()
  }

  // Function to get the colour of the top slot (0 for empty)
  def getTopColour(): Int = {
    slots.headOption.getOrElse(0)
  }

  // Function to pour colours into another vial
  def pourInto(otherVial: ChemicalVial): Int = {
    if (!canPourInto(otherVial)) {
      return 0
    }
    val numColoursToPour = math.min(countConsecutiveSameColours(), otherVial.getEmptySlots())
    // Update the slots of this vial
    slots = slots.drop(numColoursToPour) ++ Array.fill(numColoursToPour)(0)
    // Update the slots of the other vial
    otherVial.addColours(slots.take(numColoursToPour))
    numColoursToPour
  }

  // Function to add colours to this vial
  private def addColours(colours: Array[Int]): Unit = {
    var i = 0
    while (i < colours.length && !isFull()) {
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

  // Function to get the number of empty slots in the vial
  def getEmptySlots(): Int = {
    slots.count(_ == 0)
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