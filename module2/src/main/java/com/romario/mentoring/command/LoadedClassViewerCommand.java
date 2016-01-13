package com.romario.mentoring.command;

import com.romario.mentoring.container.Container;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Scanner;

/**
 * LoadedClassViewerCommand class
 */
public class LoadedClassViewerCommand extends AbstractCommand {

  private static final Logger sLogger = Logger.getLogger(LoadedClassViewerCommand.class.getName());

  @Override
  public void perform() {
    listModules();
  }

  private void listModules() {
    final Map<Integer, Object> classes = Container.getInstance().getClassesMap();
    System.out.println("List modules : ");
    for (Integer s : classes.keySet()) {
      System.out.println(s + " - " + classes.get(s).getClass().getName() + " (" + classes.get( s ).toString() + " )");
    }
    int choice = getIdModule();
    outputInformationAboutModule(choice);
  }

  private int getIdModule() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the id module");
    return scanner.nextInt();
  }

  private void outputInformationAboutModule( final int id )
  {
    final Object object = Container.getInstance().getClassesMap().get( id );
    System.out.println("===============================");
    System.out.println( "Module id = " + id );
    System.out.println( object );
    System.out.println("===============================");

  }

}
