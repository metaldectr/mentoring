package com.romario.mentoring.manager;

import java.util.Scanner;

import com.romario.mentoring.command.Command;
import com.romario.mentoring.command.CommandFactory;

/**
 * Manager class
 */
public class Manager {

  public void manage() {
    final Scanner scanner = new Scanner(System.in);
    final CommandFactory commandFactory = new CommandFactory(scanner);
    
    for (;;) {
      mainMenu();
      Command command = commandFactory.getCommand(choice(scanner));
      command.perform();
    }
  }

  public Integer choice(final Scanner scanner) {
    if(scanner.hasNextInt()) return scanner.nextInt();
    else return null;
  }

  private void mainMenu() {
    System.out.println("1 - modules");
    System.out.println("2 - load");
    System.out.println("3 - exit");
  }

}
