package com.romario.mentoring.visitor.acceptor;

import com.romario.mentoring.visitor.entity.Directory;
import com.romario.mentoring.visitor.entity.File;

/**
 *
 */
public class FileVisitable
  implements Visitable
{

  @Override
  public void visit( Directory visitor )
  {
    System.out.println("Visit Directory");
  }

  @Override
  public void visit( File file )
  {
    System.out.println("Visit File");
  }
}
