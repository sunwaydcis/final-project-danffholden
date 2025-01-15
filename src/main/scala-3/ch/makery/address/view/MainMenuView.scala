package ch.makery.address.view

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{AnchorPane, VBox}

import ch.makery.address.MainApp
import ch.makery.address.view.SelectDifficultyMenuView

object MainMenuView {
  //define the display characteristics of the MainMenuScene
  def displayMainMenuScene(): Scene = new Scene():
      root = new AnchorPane():
        prefHeight = 1000
        prefWidth = 1920

        children = new VBox():
          AnchorPane.setTopAnchor(this, 0)
          AnchorPane.setLeftAnchor(this, 0)
          AnchorPane.setRightAnchor(this, 0)
          alignment = Pos.TopCenter
          prefWidth = 100

          children = Seq(
            // Title label
            new Label("CHEMIXTRY: PROFESSIONAL") {
              alignmentInParent = Pos.Center
              style = "-fx-font-size: 24px; -fx-font-weight: bold;" // Optional styling
              VBox.setMargin(this, Insets(120, 0, 0, 0))
            },

            // Description label
            new Label(
              "Welcome to the professional mode of the chemistry mixing phenomenon, CHEMIXTRY! Time yourself, view your stats and more!"
            ) {
              alignmentInParent = Pos.Center
              wrapText = true // Allows text wrapping
              VBox.setMargin(this, Insets(100, 0, 0, 0))
            },

            // Instruction label
            new Label("Choose an option below to continue") {
              alignmentInParent = Pos.Center
              VBox.setMargin(this, Insets(24, 0, 0, 0))
            },

            // Buttons
            new Button("PLAY GAME") {
              prefWidth = 280
              onAction = _ => handlePlayGameButtonPressed()

              VBox.setMargin(this, Insets(160, 0, 0, 0))
            },

            new Button("QUIT") {
              prefWidth = 280
              onAction = _ => handleQuitButtonPressed()
              VBox.setMargin(this, Insets(40, 0, 0, 0))
            }
          )


  // Declare methods for button's onAction events
  private def handlePlayGameButtonPressed(): Unit = {
    MainApp.stage.scene = SelectDifficultyMenuView.displaySelectDifficultyMenuScene()
  }

  private def handleQuitButtonPressed(): Unit = {
    System.exit(0)
  }
}
