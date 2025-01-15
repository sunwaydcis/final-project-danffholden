/* Comments:
   Welcome to my CHEMIXTRY: PROFESSIONAL colour-mixing game where users aim to sort the colours found in multiple chemical vials.
   The inspiration for this game design came from the personal frequent use of a mobile app called 'Offline Games', game mode: 'Colour Mixer'
   More on the explanation for this project's game design will be shown in the demonstrative YouTube video here: * insert URL later *

   Overall, I hope that this project shows the lessons learnt from the subject PRG2104: Object-Oriented Programming

   With that, here are the current To-Do's:
       current commit No.: 7
       Completed in this commit:
           Fixed the randomisation of the vials for a startGame() by employing allSlots.remove(Random.nextInt...)
           Also fixed the colours being way too dark. Even though they are more similar now, because generally they are brighter, they are still distinguishable.
       Things to do in next commit:
           Employ interactivity for user selection.
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
