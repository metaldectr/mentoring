package com.romario.mentoring.visitor.acceptor;

import com.romario.mentoring.visitor.entity.FileSystemElement;

/**
 *
 */
public interface Visitable
{

  Visitable assept( FileSystemElement visitor );

}
