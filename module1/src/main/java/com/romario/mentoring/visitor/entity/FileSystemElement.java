package com.romario.mentoring.visitor.entity;

import com.romario.mentoring.visitor.acceptor.Visitable;

/**
 *
 */
public interface FileSystemElement
{

  void accept(Visitable visitor);
}
