package com.romario.mentoring.visitor.main;

import com.romario.mentoring.visitor.entity.Directory;
import com.romario.mentoring.visitor.acceptor.DirectoryVisitable;
import com.romario.mentoring.visitor.acceptor.FileVisitor;
import com.romario.mentoring.visitor.acceptor.Visitable;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class Main
{

  public static void main( String[] args )
    throws IOException
  {
    File root = new File( "/test" );
    displayIt( root );
  }

  public static void displayIt( File node )
  {
    Visitable visitable;
    if ( node.isDirectory() ) {
      visitable = new DirectoryVisitable().assept( new Directory( node ) );
      String[] subNote = node.list();
      for( String filename : subNote ) {
        displayIt( new File( node, filename ) );
      }
    } else {
      visitable = new FileVisitor().assept(
        new com.romario.mentoring.visitor.entity.File( node ) );
    }
  }

}
