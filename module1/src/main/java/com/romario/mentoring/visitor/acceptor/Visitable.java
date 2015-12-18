package com.romario.mentoring.visitor.acceptor;

import com.romario.mentoring.visitor.entity.Directory;
import com.romario.mentoring.visitor.entity.File;

/**
 *
 */
public interface Visitable
{

  void visit( Directory visitor );

  void visit( File file );

}
