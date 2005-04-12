/*
 * Copyright (c) 2001 Atanion GmbH, Germany
 * All rights reserved.
 * Created on: 19.02.2002, 17:01:56
 * $Id$
 */
package com.atanion.tobago.taglib.component;

import com.atanion.tobago.component.ComponentUtil;
import com.atanion.tobago.taglib.decl.HasId;
import com.atanion.tobago.taglib.decl.HasValue;
import com.atanion.tobago.taglib.decl.IsDisabled;
import com.atanion.tobago.taglib.decl.HasOnchangeListener;
import com.atanion.tobago.taglib.decl.IsInline;
import com.atanion.tobago.taglib.decl.HasLabel;
import com.atanion.tobago.taglib.decl.HasLabelWithAccessKey;
import com.atanion.tobago.taglib.decl.IsRendered;
import com.atanion.tobago.taglib.decl.HasBinding;
import com.atanion.tobago.taglib.decl.HasTip;
import com.atanion.tobago.taglib.decl.HasHeight;
import com.atanion.util.annotation.Tag;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectMany;

@Tag(name="selectManyListbox")
public class SelectManyListboxTag extends InputTag
    implements HasId, HasValue, IsDisabled, HasHeight, IsInline,
    HasLabel, HasLabelWithAccessKey, IsRendered, HasBinding, HasTip
    {

// /////////////////////////////////////////// constants

// /////////////////////////////////////////// attributes

  private String rows;

// /////////////////////////////////////////// constructors

// /////////////////////////////////////////// code

  public String getComponentType() {
    return UISelectMany.COMPONENT_TYPE;
  }

  protected void setProperties(UIComponent component) {
    super.setProperties(component);

   ComponentUtil.setIntegerProperty(component, ATTR_ROWS, rows, getIterationHelper());
  }

  public void release() {
    super.release();
    rows = null;
  }
  
// /////////////////////////////////////////// bean getter + setter

  public String getRows() {
    return rows;
  }

  public void setRows(String rows) {
    this.rows = rows;
  }
}
