/* Comments:
   Welcome to my CHEMIXTRY: PROFESSIONAL colour-mixing game where users aim to sort the colours found in multiple chemical vials.
   The inspiration for this game design came from the personal frequent use of a mobile app called 'Offline Games', game mode: 'Colour Mixer'
   More on the explanation for this project's game design will be shown in the demonstrative YouTube video here: * insert URL later *

   Overall, I hope that this project shows the lessons learnt from the subject PRG2104: Object-Oriented Programming

   With that, here are the current To-Do's:
       current commit No.: 2
       Completed in this commit:
           1. Created ch.makery.address package/directory structure
           2. Created a basic RootLayout.fxml file to test whether FXMLLoader and roots.get is working
       Things to do in next commit:
           1. Begin completing Menu-Navigation via the MVC directory structure
           2. Ensure that each button functionality is well though-out as the fxml files are being devleloped.
 */

package ch.makery.address

import javafx.fxml.FXMLLoader
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene as sfxs
import javafx.scene as jfxs
import scalafx.scene.Scene
import scalafx.Includes.*

object MainApp extends JFXApp3:

  var roots: Option[sfxs.layout.AnchorPane] = None

  override def start(): Unit =
    val rootResource = getClass.getResource("view/RootLayout.fxml")
    val loader = new FXMLLoader(rootResource)

    loader.load()

    roots = Option(loader.getRoot[jfxs.layout.AnchorPane])

    stage = new PrimaryStage():
      title = "CHEMIXRY: PROFESSIONAL"
      scene = new Scene():
        root = roots.get

end MainApp
