package ch.makery.address.view

import ch.makery.address.model.ChemicalVial
import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.{Group, Scene}
import scalafx.scene.control.Label
import scalafx.scene.layout.{AnchorPane, GridPane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Line, LineTo, MoveTo, Path, Rectangle}
import ch.makery.address.util.GameMethods

object GameView {

  // Define a map to associate integer color values with actual color objects
  private def assignColoursToSlotInt(vialsList: ObservableBuffer[ChemicalVial]): Map[Int, Color] =
    (1 to (vialsList.length - 2)).map(i => i -> Color.rgb(scala.util.Random.nextInt(256), scala.util.Random.nextInt(256), scala.util.Random.nextInt(256))).toMap

  def displayGameScene(vialsList: ObservableBuffer[ChemicalVial]): Scene = {
    val colorMap: Map[Int, Color] = assignColoursToSlotInt(vialsList)

    val scene = new Scene()
    val root = new AnchorPane()
    scene.root = root

    root.prefHeight = 1000
    root.prefWidth = 1920

    // Title label
    val titleLabel = new Label("CHEMIXTRY: PROFESSIONAL") {
      alignmentInParent = Pos.Center
      style = "-fx-font-size: 24px; -fx-font-weight: bold;" // Optional styling
      AnchorPane.setTopAnchor(this, 10)
      AnchorPane.setLeftAnchor(this, 10)
    }
    root.children = titleLabel

    // Create a GridPane to hold the chemical vials
    val gridPane = new GridPane() {
      hgap = 10
      vgap = 10
      AnchorPane.setTopAnchor(this, 60)
      AnchorPane.setLeftAnchor(this, 10)
      AnchorPane.setRightAnchor(this, 10)
      AnchorPane.setBottomAnchor(this, 10)
    }
    root.children += gridPane

    // Calculate the number of rows and columns for the grid
    val numVials = vialsList.length
    val numCols = math.ceil(math.sqrt(numVials)).toInt
    val numRows = (numVials + numCols - 1) / numCols

    // Add each vial to the grid pane
    for (i <- 0 until numVials; j <- 0 until numRows) {
      val vialIndex = i + j * numCols
      if (vialIndex < numVials) {
        val vial = vialsList(vialIndex)
        val vialGraphic = createChemicalVialGraphic(vial, colorMap)
        gridPane.add(vialGraphic, j, i)
      }
    }

    scene
  }

  private def createChemicalVialGraphic(vial: ChemicalVial, colorMap: Map[Int, Color]): Group = {
    // Define the vial shape with lines
    val leftLine = new Line {
      startX = 50.0
      startY = 50.0
      endX = 50.0
      endY = 150.0
      stroke = Color.Black
      strokeWidth = 2.0
    }

    val rightLine = new Line {
      startX = 100.0
      startY = 50.0
      endX = 100.0
      endY = 150.0
      stroke = Color.Black
      strokeWidth = 2.0
    }

    val bottomLine = new Line {
      startX = 50.0
      startY = 150.0
      endX = 100.0
      endY = 150.0
      stroke = Color.Black
      strokeWidth = 2.0
    }

    // Group the lines to form the vial shape
    val vialShape = new Group()
    vialShape.children ++= Seq(leftLine, rightLine, bottomLine)

    // Add colored rectangles for each color in the vial
    var yOffset = 150.0 // Start at the bottom of the vial
    for (color <- vial.getSlots().filter(_ != 0)) {
      val colorRect = new Rectangle {
        x = 50.0
        y = yOffset - 50.0
        width = 50.0 // Adjust width to fit within the vial
        height = 50.0
        fill = colorMap.getOrElse(color, Color.Black) // Use the passed-in colorMap
      }
      vialShape.children.add(colorRect) // Add rectangles as children of the Group
      yOffset -= 50.0
    }

    vialShape
  }
}