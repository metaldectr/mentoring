package com.romario.mentoring.command;

public class DefaultCommand implements Command {

  public void perform() {
    System.out.println("Incorrect input! try again");
  }

}
