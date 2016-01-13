package com.romario.mentoring.command;

import java.util.Scanner;

import com.romario.mentoring.container.Container;
import com.romario.mentoring.module.Module;

/**
 * LoadedClassViewerCommand class
 */
public class LoadedClassViewerCommand implements Command {

  private Scanner scanner;

  public LoadedClassViewerCommand(Scanner scanner) {
    this.scanner = scanner;
  }

  public void perform() {
    listModules();
  }

  private void listModules() {
    final Container container = Container.getInstance();
    System.out.println("List modules : ");
    for (Module module : container.getAll()) {
      int moduleID = module.getId();
      String moduleClassName = module.getClass().getName();
      System.out.printf("%s - %s = (%s) %n", moduleID, moduleClassName, module);
    }
    int choice = getIdModule();
    outputInformationAboutModule(choice);
  }

  private int getIdModule() {
    System.out.println("Enter the id module");
    return scanner.nextInt();
  }

  private void outputInformationAboutModule( final int id )
  {
    final Object object = Container.getInstance().get( id );
    System.out.println("===============================");
    System.out.println( "Module id = " + id );
    System.out.println( object );
    System.out.println("===============================");

  }

}
