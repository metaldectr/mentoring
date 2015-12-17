package com.romario.mentoring.visitor.acceptor;

import com.romario.mentoring.visitor.entity.FileSystemElement;

/**
 *
 */
public class FileVisitor
  implements Visitable
{
  @Override
  public Visitable assept( FileSystemElement visitor )
  {
    System.out.println( "File visited: " + visitor.getFile().getPath() );
  }
}
