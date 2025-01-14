package ch.makery.address.view

import ch.makery.address.MainApp
import javafx.fxml.FXML
import scalafx.event.ActionEvent
import scalafx.scene.control.Button


@FXML
class MainMenuController():
  @FXML
  private var playGameButton:  Button = null
  @FXML
  private var viewSavesButton: Button = null
  @FXML
  private var viewStatsButton: Button = null
  @FXML
  private var quitButton:      Button = null

  def handlePlayGameButtonPressed(action: ActionEvent): Unit =
    MainApp.showPlayGameMenu()

  def handleViewSavesButtonPressed(action: ActionEvent): Unit =
    MainApp.showSaveGamesMenu()

  def handleViewStatsButtonPressed(action: ActionEvent): Unit =
    MainApp.showUserStatsMenu()

    def handleQuitButtonPressed(action: ActionEvent): Unit =
    System.exit(0)