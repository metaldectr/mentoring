package com.romario.mentoring.visitor.entity;

import com.romario.mentoring.visitor.acceptor.Visitable;

/**
 *
 */
public class File
  implements FileSystemElement
{

  private java.io.File file;

  public File( java.io.File file )
  {
    this.file = file;
  }

  @Override
  public void accept( Visitable visitor )
  {
    visitor.visit( this );
  }
}
