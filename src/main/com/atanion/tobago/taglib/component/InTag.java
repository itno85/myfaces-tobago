/*
 * Copyright (c) 2001 Atanion GmbH, Germany
 * All rights reserved.
 * Created on: 15.02.2002, 17:01:56
 * $Id$
 */
package com.atanion.tobago.taglib.component;

import com.atanion.tobago.component.ComponentUtil;
import com.atanion.tobago.component.UIInput;
import com.atanion.tobago.taglib.decl.*;
import com.atanion.util.annotation.Tag;

import javax.faces.component.UIComponent;
@Tag(name="in", bodyContent="empty")
public class InTag extends InputTag
    implements HasValue, HasId, HasConverter, IsReadOnly, IsDisabled,
               HasWidth, HasOnchangeListener, IsInline, IsFocus, HasPassword,
               IsRequired, IsRendered,
               HasBinding, HasTip,
               // fixme: extended annotation interface ?
               HasLabel, HasLabelWithAccessKey
               //HasLabelAndAccessKey
    {

// ----------------------------------------------------------------- attributes

  private String password;


// ----------------------------------------------------------- business methods

  public String getComponentType() {
    return UIInput.COMPONENT_TYPE;
  }

  public void release() {
    super.release();
    password = null;
  }

  protected void setProperties(UIComponent component) {
    super.setProperties(component);
    ComponentUtil.setBooleanProperty(component, ATTR_PASSWORD, password,
        getIterationHelper());
  }

// ------------------------------------------------------------ getter + setter

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
