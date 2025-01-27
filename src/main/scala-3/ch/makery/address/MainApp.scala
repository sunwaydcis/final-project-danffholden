/* Comments:
   Welcome to my CHEMIXTRY: PROFESSIONAL colour-mixing game where users aim to sort the colours found in multiple chemical vials.
   The inspiration for this game design came from the personal frequent use of a mobile app called 'Offline Games', game mode: 'Colour Mixer'
   More on the explanation for this project's game design will be shown in the demonstrative YouTube video here: * insert URL later *

   Overall, I hope that this project shows the lessons learnt from the subject PRG2104: Object-Oriented Programming

   With that, here are the current To-Do's:
       current commit No.: 8
       Completed in this commit:
       
       Things to do in next commit:
 */

package ch.makery.address

import ch.makery.address.view.MainMenuView
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage

object MainApp extends JFXApp3:
  override def start(): Unit =
    // Initialize the PrimaryStage
    stage = new PrimaryStage():
      title = "CHEMIXTRY: PROFESSIONAL"
      scene = MainMenuView.displayMainMenuScene()
end MainApp
