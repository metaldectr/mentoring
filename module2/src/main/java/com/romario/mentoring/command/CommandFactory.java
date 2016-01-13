package com.romario.mentoring.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandFactory {

  private static final Map<Integer, Command> FACTORY_MAPPING_CONFIG = new HashMap<Integer, Command>();

  public CommandFactory(Scanner scanner) {
    FACTORY_MAPPING_CONFIG.put(1, new LoadedClassViewerCommand(scanner));
    FACTORY_MAPPING_CONFIG.put(2, new ClassLoaderCommand(scanner));
    FACTORY_MAPPING_CONFIG.put(3, new ExitCommand(scanner));
    FACTORY_MAPPING_CONFIG.put(null, new DefaultCommand());
  }

  public Command getCommand(Integer choce) {
    Command defaultCommand = FACTORY_MAPPING_CONFIG.get(null);
    Command command = FACTORY_MAPPING_CONFIG.get(choce);
    return (command == null) ? defaultCommand : command;
  }

}
