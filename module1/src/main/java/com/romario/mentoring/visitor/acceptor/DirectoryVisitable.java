package com.romario.mentoring.visitor.acceptor;

import com.romario.mentoring.visitor.entity.FileSystemElement;

/**
 *
 */
public class DirectoryVisitable implements Visitable
{
  @Override
  public Visitable assept( FileSystemElement visitor )
  {
    System.out.println("DIrectory visited: " + visitor.getFile().getPath());
  }
}
