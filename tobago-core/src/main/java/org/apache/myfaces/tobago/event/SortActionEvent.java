package org.apache.myfaces.tobago.event;

/*
 * Copyright 2002-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.myfaces.tobago.component.UIData;

import javax.faces.component.UIColumn;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;

public class SortActionEvent extends ActionEvent {

  private UIColumn column;

  public SortActionEvent(UIData sheet, UIColumn column) {
    super(sheet);
    this.column = column;
    setPhaseId(PhaseId.INVOKE_APPLICATION);
  }

  /**
   * Returns the UIColumn object for which this event is triggert.
   *
   * @return UIColumn
   */
  public UIColumn getColumn() {
    return column;
  }

  /**
   * Convenience method to get the UIData Component. 
   */
  public UIData getSheet() {
    return (UIData) getComponent();
  }
}
