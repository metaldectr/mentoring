package com.romario.mentoring.bridge.manager;

import com.romario.mentoring.bridge.api.DrawAPI;

import javax.swing.*;
import java.awt.*;

/**
 * GUIManager
 */
public class GUIManager
{

  protected DrawAPI drawAPI;

  public Window createWindow() {
    return new JWindow(  );
  }

  public Button createButton() {
    drawAPI.drawLine();
    drawAPI.drawLine();
    drawAPI.drawLine();
    drawAPI.drawLine();
    return new Button(  );
  }

  public Panel createPanel() {
    drawAPI.drawLine();
    drawAPI.drawLine();
    drawAPI.drawLine();
    return new Panel();
  }


  public DrawAPI getDrawAPI()
  {
    return drawAPI;
  }

  public void setDrawAPI( DrawAPI drawAPI )
  {
    this.drawAPI = drawAPI;
  }
}
