package com.romario.mentoring.command;

import java.util.Scanner;

public class ExitCommand implements Command {

  private static final int NORMAL_EXIT_STATUS_CODE = 0;
  private Scanner scanner;

  public ExitCommand(final Scanner scanner) {
    this.scanner = scanner;
  }

  public void perform() {
    System.out.println("EXIT");
    scanner.close();
    System.exit(NORMAL_EXIT_STATUS_CODE);
  }


}
