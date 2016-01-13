package com.romario.mentoring.classloader;

import com.romario.mentoring.container.Container;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * MyClassLoader class
 */
public class MyClassLoader extends ClassLoader {

  private Map<String, Class<?>> cache = new HashMap<String, Class<?>>();
  private final static Logger sLogger = Logger.getLogger(MyClassLoader.class);
  private static final String PATH = "d:\\projects\\mentoring\\module2\\out\\";
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

      final JarEntry jarEntry = jarFile.getJarEntry( name + ".java" );
      InputStream stream = jarFile.getInputStream( jarEntry );
      ByteArrayOutputStream out = new ByteArrayOutputStream(  );
      int value = stream.read();
      while ( -1 != value) {
        out.write( value );
        value = stream.read();
      }
      byte[] classByte = out.toByteArray();
      clazz = defineClass( name, classByte, 0, classByte.length, null );
      sLogger.info( "Load class with " + MyClassLoader.class.getName() + " with name : " + name );
      return clazz;
    } catch( IOException e ) {
      sLogger.error( "Invalid path to jar file", e );
      return null;
    }

  }

    public Class<?> loadClass ( String name, String moduleName)
    throws ClassNotFoundException
    {
      Class<?> clazz = null;
      clazz = cache.get( name );
      if (clazz != null) {
        return clazz;
      } else {
        clazz = findClass( name );
        try {

          final Method methodSetId = clazz.getMethod( "setId", int.class );
          Object obj = clazz.newInstance();
          methodSetId.invoke( obj, Container.getInstance().getClassesMap().size() );

          final Method methodSetName = clazz.getMethod( "setName", String.class );
          methodSetName.invoke( obj, moduleName );

          cache.put( MyClassLoader.class.getCanonicalName(), clazz );
          Container.getInstance().getClassesMap().put( Container.getInstance().getClassesMap().size(), obj );
        } catch( NoSuchMethodException nsmExc ) {
          sLogger.error( "No such method in the class", nsmExc );
        } catch( InstantiationException e ) {
          sLogger.error(
            "Filed to create an instance of a class using the newInstance method",
            e );
        } catch( IllegalAccessException e ) {
          sLogger.error( "Invalid access", e );
        } catch( InvocationTargetException e ) {
          sLogger.error( "Couldn't invoke method ", e );
        }
      }
      return clazz;
    }

  private int getIdForClass() {
    return Container.getInstance().getClassesMap().size();
  }

  private String getNameForClass(String name) {
    return name + Container.getInstance().getClassesMap().size();
  }

}
