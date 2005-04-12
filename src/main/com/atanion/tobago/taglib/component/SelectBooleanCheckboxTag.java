/*
 * Copyright (c) 2001 Atanion GmbH, Germany
 * All rights reserved.
 * Created on: 15.02.2002, 17:01:56
 * $Id$
 */
package com.atanion.tobago.taglib.component;

import com.atanion.util.annotation.Tag;
import com.atanion.tobago.taglib.decl.HasId;
import com.atanion.tobago.taglib.decl.HasLabel;
import com.atanion.tobago.taglib.decl.HasLabelWithAccessKey;
import com.atanion.tobago.taglib.decl.HasValue;
import com.atanion.tobago.taglib.decl.IsDisabled;
import com.atanion.tobago.taglib.decl.IsInline;
import com.atanion.tobago.taglib.decl.IsRendered;
import com.atanion.tobago.taglib.decl.HasBinding;
import com.atanion.tobago.taglib.decl.HasTip;

import javax.faces.component.UISelectBoolean;

@Tag(name="selectBooleanCheckbox", bodyContent="empty")
public class SelectBooleanCheckboxTag extends InputTag
    implements HasId, HasLabel, HasLabelWithAccessKey, HasValue, IsDisabled,
               IsInline, IsRendered, HasBinding, HasTip
    {

// ----------------------------------------------------------- business methods

  public String getComponentType() {
    return UISelectBoolean.COMPONENT_TYPE;
  }
}
