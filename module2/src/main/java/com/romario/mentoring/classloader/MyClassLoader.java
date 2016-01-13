package com.romario.mentoring.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

/**
 * MyClassLoader class
 */
public class MyClassLoader extends ClassLoader {

  private final static Logger sLogger = Logger.getLogger(MyClassLoader.class);
  private static final String PATH = "d:\\projects\\mentoring\\module2\\out\\Module.jar";
  /*com.romario.mentoring.module.Module*/

  public MyClassLoader() {
    super(MyClassLoader.class.getClassLoader());
  }

  @Override
  protected Class<?> findClass( String name )
    throws ClassNotFoundException
  {
    Class<?> clazz;
    clazz = findSystemClass( name );
    if (clazz != null) {
      return clazz;
    }

    JarFile jarFile = null;
    try {
      jarFile = new JarFile( PATH );

      final JarEntry jarEntry = jarFile.getJarEntry( name.substring( name.lastIndexOf( "." ) + 1, name.length() ) + ".class" );
      InputStream stream = jarFile.getInputStream( jarEntry );
      byte[] classByte = getClassBytes(stream);
      clazz = defineClass( name, classByte, 0, classByte.length, null );
      sLogger.info( "Load class with " + MyClassLoader.class.getName() + " with name : " + name );
      return clazz;
    } catch( IOException e ) {
      sLogger.error( "Invalid path to jar file", e );
      return null;
    }

  }

  private byte[] getClassBytes(InputStream stream) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream(  );
    int value = stream.read();
    while ( -1 != value) {
      out.write( value );
      value = stream.read();
    }
    byte[] classByte = out.toByteArray();
    return classByte;
  }
}
