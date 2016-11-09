/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.myfaces.tobago.internal.taglib.component;

import org.apache.myfaces.tobago.apt.annotation.Facet;
import org.apache.myfaces.tobago.apt.annotation.Tag;
import org.apache.myfaces.tobago.apt.annotation.UIComponentTag;
import org.apache.myfaces.tobago.component.Facets;
import org.apache.myfaces.tobago.component.RendererTypes;
import org.apache.myfaces.tobago.internal.taglib.declaration.HasCollapsedMode;
import org.apache.myfaces.tobago.internal.taglib.declaration.HasIdBindingAndRendered;
import org.apache.myfaces.tobago.internal.taglib.declaration.HasLabel;
import org.apache.myfaces.tobago.internal.taglib.declaration.HasTip;
import org.apache.myfaces.tobago.internal.taglib.declaration.IsCollapsible;
import org.apache.myfaces.tobago.internal.taglib.declaration.IsVisual;

import javax.faces.component.UIPanel;

/**
 * Renders a panel with border and title.
 */
@Tag(name = "box")
@UIComponentTag(
    uiComponent = "org.apache.myfaces.tobago.component.UIBox",
    uiComponentBaseClass = "org.apache.myfaces.tobago.internal.component.AbstractUIBox",
    uiComponentFacesClass = "javax.faces.component.UIPanel",
    componentFamily = UIPanel.COMPONENT_FAMILY,
    rendererType = RendererTypes.BOX,
    facets = {
        @Facet(name = Facets.BAR,
            description = "Contains some code to be placed in the header at the bar position."),
        @Facet(name = Facets.LABEL,
            description = "Contains some code to be placed in the header at the label position."),
        @Facet(name = Facets.MENU_BAR, description = "Deprecated!"),
        @Facet(name=Facets.LAYOUT, description = "Deprecated! Contains an layout manager. "
            + "The layout manager tag should surround the content instead.")})

public interface BoxTagDeclaration
    extends HasIdBindingAndRendered, IsVisual, HasLabel, HasTip, IsCollapsible, HasCollapsedMode {
}
