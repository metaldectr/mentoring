package com.romario.mentoring.manager;

import com.romario.mentoring.command.AbstractCommand;
import com.romario.mentoring.command.ClassLoaderCommand;
import com.romario.mentoring.command.LoadedClassViewerCommand;

import java.util.Scanner;

/**
 * Manager class
 */
public class Manager {

  private AbstractCommand loadClassCommand;
  private AbstractCommand viewClassesCommand;

  public void manage() {
    final Scanner scanner = new Scanner(System.in);
    int choice = 0;
    do {
      mainMenu();
      choice = getChoice(scanner);
      switch (choice) {
        case 1:
          //view all loaded modules
          viewLoadedClassCommand(viewClassesCommand);
          break;
        case 2:
          //load module
          loadClassCommand(loadClassCommand);
          break;
        default: {
          System.out.println("Incorrect input! try again");
        }
      }
    } while (choice != 3);
  }

  public int getChoice(final Scanner scanner) {
    try {
      return Integer.parseInt(scanner.nextLine());
    } catch (final NumberFormatException exception) {
      return 0;
    }
  }

  private void mainMenu() {
    System.out.println("1 - modules");
    System.out.println("2 - load");
    System.out.println("3 - exit");
  }

  private void loadClassCommand(AbstractCommand command) {
    command = new ClassLoaderCommand();
    String path = answerMessage("Enter the path to class in module");
    String moduleName = answerMessage("Enter the module name");
    command.setPath(path);
    command.setModuleName(moduleName);
    command.perform();
  }


  private String answerMessage(String message) {
    System.out.println(message + ": ");
    return new Scanner(System.in).nextLine();
  }

  private void viewLoadedClassCommand(AbstractCommand command) {
    command = new LoadedClassViewerCommand();
    command.perform();
  }

}
