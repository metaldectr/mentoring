package com.romario.mentoring.bridge.main;

import com.romario.mentoring.bridge.api.impl.WindowsDrawApiImpl;
import com.romario.mentoring.bridge.manager.GUIManager;
import com.romario.mentoring.bridge.manager.XGUIManager;

/**
 * Main class
 */
public class Main
{
  private GUIManager guiManager = new XGUIManager();

  public static void main( String[] args )
  {
    GUIManager guiManager = new XGUIManager();
    guiManager.setDrawAPI( new WindowsDrawApiImpl() );
    guiManager.createWindow();
    guiManager.createPanel();
    guiManager.createButton();
  }
}
