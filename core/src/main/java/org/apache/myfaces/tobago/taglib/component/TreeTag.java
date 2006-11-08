package org.apache.myfaces.tobago.taglib.component;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.apache.myfaces.tobago.TobagoConstants.ATTR_DISABLED_REFERENCE;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_ID_REFERENCE;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_MUTABLE;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_NAME_REFERENCE;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_REQUIRED;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_SELECTABLE;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_SHOW_ICONS;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_SHOW_JUNCTIONS;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_SHOW_ROOT;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_SHOW_ROOT_JUNCTION;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_STATE;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_VALUE;
import static org.apache.myfaces.tobago.TobagoConstants.ATTR_MODE;
import org.apache.myfaces.tobago.component.ComponentUtil;
import org.apache.myfaces.tobago.component.UITree;

import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;


public class TreeTag extends TobagoTag implements TreeTagDeclaration {

  private String value;
  private String state;

  private String showJunctions;
  private String showIcons;
  private String showRoot;
  private String showRootJunction;

  private String selectable;
  private String mutable;

  private String idReference;
  private String nameReference;
  private String disabledReference;

  private String required;

  private String actionListener;

  private String mode;

  public String getComponentType() {
    return UITree.COMPONENT_TYPE;
  }

  protected void setProperties(UIComponent component) {
    super.setProperties(component);

    ComponentUtil.setStringProperty(component, ATTR_VALUE, value);
    ComponentUtil.setValueBinding(component, ATTR_STATE, state);

    ComponentUtil.setBooleanProperty(component, ATTR_SHOW_JUNCTIONS, showJunctions);
    ComponentUtil.setBooleanProperty(component, ATTR_SHOW_ICONS, showIcons);
    ComponentUtil.setBooleanProperty(component, ATTR_SHOW_ROOT, showRoot);
    ComponentUtil.setBooleanProperty(component, ATTR_SHOW_ROOT_JUNCTION, showRootJunction);

    ComponentUtil.setStringProperty(component, ATTR_SELECTABLE, selectable);
    ComponentUtil.setBooleanProperty(component, ATTR_MUTABLE, mutable);

    ComponentUtil.setStringProperty(component, ATTR_ID_REFERENCE, idReference);
    ComponentUtil.setStringProperty(component, ATTR_NAME_REFERENCE, nameReference);
    ComponentUtil.setStringProperty(component, ATTR_DISABLED_REFERENCE, disabledReference);
    ComponentUtil.setBooleanProperty(component, ATTR_REQUIRED, required);
    ComponentUtil.setActionListener((ActionSource) component, actionListener);
    ComponentUtil.setStringProperty(component, ATTR_MODE, mode);
  }

  public void release() {
    super.release();
    value = null;
    state = null;
    showJunctions = null;
    showIcons = null;
    showRoot = null;
    showRootJunction = null;
    selectable = null;
    mutable = null;
    idReference = null;
    nameReference = null;
    disabledReference = null;
    required = null;
    actionListener = null;
    mode = null;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getShowIcons() {
    return showIcons;
  }

  public void setActionListener(String actionListener) {
    this.actionListener = actionListener;
  }
  public String getActionListener() {
    return actionListener;
  }

  public void setShowIcons(String showIcons) {
    this.showIcons = showIcons;
  }

  public String getShowJunctions() {
    return showJunctions;
  }

  public void setShowJunctions(String showJunctions) {
    this.showJunctions = showJunctions;
  }

  public String getShowRoot() {
    return showRoot;
  }

  public void setShowRoot(String showRoot) {
    this.showRoot = showRoot;
  }

  public String getShowRootJunction() {
    return showRootJunction;
  }

  public void setShowRootJunction(String showRootJunction) {
    this.showRootJunction = showRootJunction;
  }

  public String getIdReference() {
    return idReference;
  }

  public void setIdReference(String idReference) {
    this.idReference = idReference;
  }

  public String getSelectable() {
    return selectable;
  }

  public void setSelectable(String selectable) {
    this.selectable = selectable;
  }

  public String getMutable() {
    return mutable;
  }

  public void setMutable(String mutable) {
    this.mutable = mutable;
  }

  public String getNameReference() {
    return nameReference;
  }

  public void setNameReference(String nameReference) {
    this.nameReference = nameReference;
  }

  public String getDisabledReference() {
    return disabledReference;
  }

  public void setDisabledReference(String disabledReference) {
    this.disabledReference = disabledReference;
  }

  public String getRequired() {
    return required;
  }

  public void setRequired(String required) {
    this.required = required;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }
}
