package com.romario.mentoring.model;

/**
 * MyReader model class
 */
public class MyReader
{
  private boolean flag = true;
  private String name;

  public MyReader( String name )
  {
    this.name = name;
  }

  public void doPrint( Instruction instruction, MyReader reader )
  {

    while ( isFlag() ) {
      if ( instruction.getReader() != this ) {
        try {
          Thread.sleep( 1 );
        } catch( InterruptedException e ) {
          continue;
        }
        continue;
      }

      if ( reader.isFlag() ) {
        System.out.printf(
          "Do print : ", getName() );
        instruction.setReader( reader );
        continue;
      }

      instruction.use();
      setFlag( false );
      System.out.printf(
        "Do Print : ", getName() );
      instruction.setReader( reader );
    }
  }

  public boolean isFlag()
  {
    return flag;
  }

  public void setFlag( boolean flag )
  {
    this.flag = flag;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }
}
