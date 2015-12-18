package com.romario.mentoring.visitor.main;

import com.romario.mentoring.visitor.acceptor.DirectoryVisitable;
import com.romario.mentoring.visitor.entity.Directory;
import com.romario.mentoring.visitor.entity.FileSystemElement;

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
    File root = new File( "test" );
    displayIt( root );
  }

  public static void displayIt( File node )
  {
    FileSystemElement fse = new Directory( node );
    fse.accept( new DirectoryVisitable() );

  }

}
