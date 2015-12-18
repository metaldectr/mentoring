package com.romario.mentoring.visitor.entity;

import com.romario.mentoring.visitor.acceptor.Visitable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Directory
  implements FileSystemElement
{

  private List<FileSystemElement> files;

  public Directory( java.io.File file )
  {
    if ( file.isDirectory() ) {
      this.files = new ArrayList<>();
      List<java.io.File> fileList = Arrays.asList( file.listFiles() );
      for( java.io.File f : fileList ) {
        if ( f.isDirectory() ) {
          files.add( new Directory( f ) );
        } else {
          files.add( new File( f ) );
        }
      }
    }
  }

  @Override
  public void accept( Visitable visitor )
  {
    for( FileSystemElement file : files ) {
      file.accept( visitor );
    }
    visitor.visit( this );
  }
}
