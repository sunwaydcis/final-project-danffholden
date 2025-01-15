package ch.makery.address.view

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{AnchorPane, VBox}
import ch.makery.address.util.GameMethods

import ch.makery.address.MainApp
import ch.makery.address.util.GameMethods
import ch.makery.address.view.GameView

object SelectDifficultyMenuView {
  //define the display characteristics of the PlayGameMenuScene
  def displaySelectDifficultyMenuScene(): Scene = new Scene():
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
            "Choose your difficulty!"
          ) {
            alignmentInParent = Pos.Center
            wrapText = true // Allows text wrapping
            VBox.setMargin(this, Insets(100, 0, 0, 0))
          },

          // Buttons
          new Button("EXTREME MODE") {
            prefWidth = 280
            onAction = _ => handleDifficultyButtonPressed("Extreme")

            VBox.setMargin(this, Insets(160, 0, 0, 0))
          },

          new Button("HARD MODE") {
            prefWidth = 280
            onAction = _ => handleDifficultyButtonPressed("Hard")
            VBox.setMargin(this, Insets(40, 0, 0, 0))
          },

          new Button("EASY MODE") {
            prefWidth = 280
            onAction = _ => handleDifficultyButtonPressed("Easy")
            VBox.setMargin(this, Insets(40, 0, 0, 0))
          },

          new Button("BACK") {
            prefWidth = 280
            onAction = _ => handleBackButtonPressed()
            VBox.setMargin(this, Insets(40, 0, 0, 0))
          }
        )

  def handleDifficultyButtonPressed(difficulty: String): Unit =
    val vialsBuffer = GameMethods.startGame(difficulty)
    MainApp.stage.scene = GameView.displayGameScene(vialsBuffer)
    var count = 0
    for (vial <- vialsBuffer) {
      println(s"Vial $count")
      count += 1
      for (slot <- vial.getSlots()) {
        print(s"$slot, ")
      }
    println("")
    }

  def handleBackButtonPressed(): Unit =
    MainApp.stage.scene = MainMenuView.displayMainMenuScene()
}