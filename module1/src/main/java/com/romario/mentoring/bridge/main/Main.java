package com.romario.mentoring.bridge.main;

import com.romario.mentoring.bridge.api.impl.WindowsDrawApiImpl;
import com.romario.mentoring.bridge.manager.GUIManager;
import com.romario.mentoring.bridge.manager.XGUIManager;

/**
 *
 */
public class Main
{
  private GUIManager guiManager = new XGUIManager();

  public static void main(String[] args) {

    Main main = new Main();
    main.getGuiManager().setDrawAPI( new WindowsDrawApiImpl() );
    main.getGuiManager().createWindow();
    main.getGuiManager().createPanel();
    main.getGuiManager().createButton();
  }

  public GUIManager getGuiManager()
  {
    return guiManager;
  }

  public void setGuiManager(
    GUIManager guiManager )
  {
    this.guiManager = guiManager;
  }
}
