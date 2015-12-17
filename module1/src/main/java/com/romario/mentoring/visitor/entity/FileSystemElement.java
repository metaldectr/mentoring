package com.romario.mentoring.visitor.entity;

/**
 *
 */
public class FileSystemElement
{

  private java.io.File file;

  public FileSystemElement( java.io.File file )
  {
    this.file = file;
  }

  public java.io.File getFile()
  {
    return file;
  }

  public void setFile( java.io.File file )
  {
    this.file = file;
  }

}
