/* Comments:
   Welcome to my CHEMIXTRY: PROFESSIONAL colour-mixing game where users aim to sort the colours found in multiple chemical vials.
   The inspiration for this game design came from the personal frequent use of a mobile app called 'Offline Games', game mode: 'Colour Mixer'
   More on the explanation for this project's game design will be shown in the demonstrative YouTube video here: * insert URL later *

   Overall, I hope that this project shows the lessons learnt from the subject PRG2104: Object-Oriented Programming

   With that, here are the current To-Do's:
       current commit No.: 3
       Completed in this commit:
           1. Created StartScreenLayout.fxml
               ~ contains New/Continue Game (call showPlayGameMenu)
               ~ contains Level History     (call showSaveGamesMenu)
               ~ contains Your Stats        (call showUserStatsMenu)
       Things to do in next commit:
           Fix the fxml button's onAction method calls not resolving properly.
 */

package ch.makery.address

import javafx.fxml.FXMLLoader
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene as sfxs
import javafx.scene as jfxs
import scalafx.scene.Scene
import scalafx.Includes.*
import ch.makery.address.view.MainMenuController
import scalafx.event.ActionEvent

import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes.*
import javafx.fxml.FXMLLoader
import javafx.scene as jfxs
import scalafx.scene as sfxs

object MainApp extends JFXApp3:

  private var primaryStage: Option[PrimaryStage] = None

  override def start(): Unit =
    // Initialize the PrimaryStage
    primaryStage = Some(new PrimaryStage():
      title = "CHEMIXRY: PROFESSIONAL"
      scene = new Scene() // Initially empty
    )
    // Show the main menu
    showMainMenu()

  def showMainMenu(): Unit =
    loadScene("view/MainMenuLayout.fxml")

  def showPlayGameMenu(): Unit =
    loadScene("view/PlayGameMenuLayout.fxml")

  def showSaveGamesMenu(): Unit =
    loadScene("view/SaveGamesMenuLayout.fxml")

  def showUserStatsMenu(): Unit =
    loadScene("view/UserStatsMenuLayout.fxml")

  private def loadScene(fxmlPath: String): Unit =
    val resource = getClass.getResource(fxmlPath)
    val loader = new FXMLLoader(resource)
    loader.load()
    // Retrieve the root layout from the FXML
    val roots = Option(loader.getRoot[jfxs.layout.AnchorPane]) // Use the common base type Pane
    // Update the scene of the primaryStage
    primaryStage.foreach { stage =>
      stage.scene = new Scene():
        root = roots.get
    }

////
//
//
//
//  object MainApp extends JFXApp3:
//
//    var roots: Option[sfxs.layout.AnchorPane] = None
//
//
//    override def start(): Unit =
//      val rootResource = getClass.getResource("view/MainMenuLayout.fxml")
//      val loader = new FXMLLoader(rootResource)
//
//      loader.load()
//
//      roots = Option(loader.getRoot[jfxs.layout.AnchorPane])
//
//      stage = new PrimaryStage():
//        title = "CHEMIXRY: PROFESSIONAL"
//        scene = new Scene():
//          root = roots.get
//      showMainMenu()
//
//
//
//
//
//    def showMainMenu() =
//      val resource = getClass.getResource("view/MainMenuLayout.fxml")
//      val loader = new FXMLLoader(resource)
//
//      loader.load()
//
//      val roots = loader.getRoot[jfxs.layout.AnchorPane]
//      stage.scene = new Scene():
//        root = roots.get
//

end MainApp
