package ch.makery.address.view

import ch.makery.address.model.ChemicalVial
import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.{Group, Scene}
import scalafx.scene.control.Label
import scalafx.scene.layout.{AnchorPane, GridPane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Line, LineTo, MoveTo, Path, Rectangle}
import ch.makery.address.util.GameMethods
import scalafx.scene.input.MouseEvent
import scalafx.Includes.*
import ch.makery.address.MainApp

object GameView {
  //a value to extract the index of the ChemicalVial during the onClicked MouseEvent listener for the first vial
  private var selectedVialIndex: Option[Int] = None

  // Define a map to associate integer color values with actual color objects

  def displayGameScene(vialsList: ObservableBuffer[ChemicalVial], colorMap: Map[Int, Color]): Scene = {
    val scene = new Scene()
    val root = new AnchorPane()
    scene.root = root

    root.prefHeight = 1000
    root.prefWidth = 1920


    // Create a VBox to hold the title and grid pane
    val contentVBox = new VBox() {
      spacing = 10.0 // Add spacing between elements
      alignment = Pos.TopCenter
    }

    // Title label
    val titleLabel = new Label("CHEMIXTRY: PROFESSIONAL") {
      alignment = Pos.TopCenter
      style = "-fx-font-size: 24px; -fx-font-weight: bold;" // Optional styling
      VBox.setMargin(this, Insets(120, 0, 0, 0))
    }
    contentVBox.children = titleLabel

    // Create a GridPane to hold the chemical vials
    val gridPane = new GridPane() {
      hgap = 36
      vgap = 12
      VBox.setMargin(this, Insets(80, 0, 0, 0))
      alignment = Pos.TopCenter
    }
    contentVBox.children += gridPane

    // Add each vial to the grid pane
    //    val testVial = new ChemicalVial()
    //    testVial.setSlots(Array(2, 1, 1, 1))
    //    vialsList.append(testVial)
    for (i <- 0 until vialsList.length) {
      val vial = vialsList(i)
      val vialGraphic = createChemicalVialGraphic(vial, colorMap, vialsList, gridPane)
      gridPane.add(vialGraphic, i % 4, i / 4)
    }


    // Add the VBox to the AnchorPane with constraints to center it
    AnchorPane.setTopAnchor(contentVBox, 0.0)
    AnchorPane.setLeftAnchor(contentVBox, 0.0)
    AnchorPane.setRightAnchor(contentVBox, 0.0)
    AnchorPane.setBottomAnchor(contentVBox, 0.0)
    root.children = contentVBox

    scene
  }

  private def createChemicalVialGraphic(vial: ChemicalVial, colorMap: Map[Int, Color], vialsList: ObservableBuffer[ChemicalVial], gridPane: GridPane): Group = {
    // Define the vial shape with lines
    val leftRim = new Line {
      startX = 5
      startY = 0
      endX = 0
      endY = 5
      stroke = Color.Black
      strokeWidth = 4.0
    }

    val rightRim = new Line {
      startX = 55
      startY = 0
      endX = 60
      endY = 5
      stroke = Color.Black
      strokeWidth = 4.0
    }


    val leftLine = new Line {
      startX = 5
      startY = 0
      endX = 5
      endY = 220
      stroke = Color.Black
      strokeWidth = 4.0
    }

    val rightLine = new Line {
      startX = 55
      startY = 0
      endX = 55
      endY = 220
      stroke = Color.Black
      strokeWidth = 4.0
    }

    val bottomLine = new Line {
      startX = 5
      startY = 220
      endX = 55
      endY = 220
      stroke = Color.Black
      strokeWidth = 4
    }

    // Group the lines to form the vial shape
    val vialShape = new Group()
    vialShape.children ++= Seq(leftRim, rightRim, leftLine, rightLine, bottomLine)

    // Add colored rectangles for each color in the vial
    var yOffset = 218.0 // Start at the bottom of the vial
    for (color <- vial.getSlots().filter(_ != 0)) {
      val colorRect = new Rectangle {
        x = 7
        y = yOffset - 50.0
        width = 46.0 // Adjust width to fit within the vial
        height = 50.0
        fill = colorMap.getOrElse(color, Color.Black) // Use the passed-in colorMap
        stroke = Color.Black
        strokeWidth = 2
      }
      vialShape.children.add(colorRect) // Add rectangles as children of the Group
      yOffset -= 50.0
    }

    //declare listener event for the user to select Vials.
    vialShape.onMouseClicked = (event: MouseEvent) => {
      selectedVialIndex match {
        case None =>
          selectedVialIndex = Some(vialsList.indexOf(vial))
        case Some(selectedIndex) =>
          val firstVialIndex = selectedIndex
          val secondVialIndex = vialsList.indexOf(vial)
          val firstVial = vialsList(firstVialIndex)
          val secondVial = vialsList(secondVialIndex)

          val (updatedFirstVial, updatedSecondVial) = firstVial.pourInto(secondVial)

          // Update the vialsList with the updated vials
          vialsList.update(firstVialIndex, updatedFirstVial)
          vialsList.update(secondVialIndex, updatedSecondVial)

          MainApp.stage.scene = GameView.displayGameScene(vialsList, colorMap)


          selectedVialIndex = None
      }
    }

    vialShape
  }
}