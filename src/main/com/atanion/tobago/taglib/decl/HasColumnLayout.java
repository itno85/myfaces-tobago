package com.atanion.tobago.taglib.decl;

import com.atanion.util.annotation.TagAttribute;
import com.atanion.util.annotation.UIComponentTagAttribute;

/**
 * $Id$
 */
public interface HasColumnLayout {
  /**
   *  <![CDATA[
   * LayoutConstraints for column layout.
   * Semicolon separated list of layout tokens ('<x>*', '<x>px' or '<x>%').     
   *    ]]>
   */
  @TagAttribute @UIComponentTagAttribute(type=String.class)
  public void setColumns(String columns);
}
