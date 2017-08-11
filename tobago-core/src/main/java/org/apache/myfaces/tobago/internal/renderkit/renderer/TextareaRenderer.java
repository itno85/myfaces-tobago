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

package org.apache.myfaces.tobago.internal.renderkit.renderer;

import org.apache.myfaces.tobago.component.UITextarea;
import org.apache.myfaces.tobago.config.TobagoConfig;
import org.apache.myfaces.tobago.internal.component.AbstractUITextarea;
import org.apache.myfaces.tobago.internal.util.AccessKeyLogger;
import org.apache.myfaces.tobago.internal.util.HtmlRendererUtils;
import org.apache.myfaces.tobago.internal.util.JsonUtils;
import org.apache.myfaces.tobago.internal.util.RenderUtils;
import org.apache.myfaces.tobago.internal.util.StringUtils;
import org.apache.myfaces.tobago.renderkit.css.BootstrapClass;
import org.apache.myfaces.tobago.renderkit.css.TobagoClass;
import org.apache.myfaces.tobago.renderkit.html.HtmlAttributes;
import org.apache.myfaces.tobago.renderkit.html.HtmlElements;
import org.apache.myfaces.tobago.sanitizer.SanitizeMode;
import org.apache.myfaces.tobago.sanitizer.Sanitizer;
import org.apache.myfaces.tobago.util.ComponentUtils;
import org.apache.myfaces.tobago.webapp.TobagoResponseWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;
import javax.faces.validator.RegexValidator;
import javax.faces.validator.Validator;
import java.io.IOException;

public class TextareaRenderer extends MessageLayoutRendererBase {

  private static final Logger LOG = LoggerFactory.getLogger(TextareaRenderer.class);

  @Override
  protected void setSubmittedValue(
      final FacesContext facesContext, final EditableValueHolder component, final String newValue) {

    String value = newValue;

    final AbstractUITextarea textarea = (AbstractUITextarea) component;
    if (ComponentUtils.getDataAttribute(textarea, "html-editor") != null
        && SanitizeMode.auto == textarea.getSanitize()) {
      final Sanitizer sanitizer = TobagoConfig.getInstance(facesContext).getSanitizer();
      value = sanitizer.sanitize(newValue);
    }

    // tbd: should this be configurable?
    if (TobagoConfig.getInstance(facesContext).isDecodeLineFeed()) {
      value = value.replace("\r\n", "\n");
    }

    super.setSubmittedValue(facesContext, textarea, value);
  }

  @Override
  public void encodeBeginField(final FacesContext facesContext, final UIComponent component) throws IOException {
    if (!(component instanceof UITextarea)) {
      LOG.error("Wrong type: Need " + UITextarea.class.getName() + ", but was " + component.getClass().getName());
      return;
    }

    final UITextarea input = (UITextarea) component;
    final String title = HtmlRendererUtils.getTitleFromTipAndMessages(facesContext, component);
    final String clientId = input.getClientId(facesContext);
    final String fieldId = input.getFieldId(facesContext);
    final TobagoResponseWriter writer = getResponseWriter(facesContext);
    final Integer rows = input.getRows();
    final boolean readonly = input.isReadonly();
    final boolean disabled = input.isDisabled();

    writer.startElement(HtmlElements.TEXTAREA);
    writer.writeNameAttribute(clientId);
    writer.writeIdAttribute(fieldId);
    HtmlRendererUtils.writeDataAttributes(facesContext, writer, input);
    writer.writeAttribute(HtmlAttributes.ROWS, rows);
    writer.writeAttribute(HtmlAttributes.TITLE, title, true);
    writer.writeAttribute(HtmlAttributes.READONLY, readonly);
    writer.writeAttribute(HtmlAttributes.DISABLED, disabled);
    writer.writeAttribute(HtmlAttributes.REQUIRED, input.isRequired());
    writer.writeAttribute(HtmlAttributes.TABINDEX, input.getTabIndex());

    if (input.getAccessKey() != null) {
      writer.writeAttribute(HtmlAttributes.ACCESSKEY, Character.toString(input.getAccessKey()), false);
      AccessKeyLogger.addAccessKey(facesContext, input.getAccessKey(), clientId);
    }

    writer.writeClassAttribute(
        TobagoClass.TEXTAREA,
        TobagoClass.TEXTAREA.createMarkup(input.getMarkup()),
        TobagoClass.TEXTAREA.createDefaultMarkups(input),
        BootstrapClass.FORM_CONTROL,
        input.getCustomClass());
    writer.writeStyleAttribute(input.getStyle());
    int maxLength = 0;
    int minLength = 0;
    String pattern = null;
    for (final Validator validator : input.getValidators()) {
      if (validator instanceof LengthValidator) {
        final LengthValidator lengthValidator = (LengthValidator) validator;
        maxLength = lengthValidator.getMaximum();
        minLength = lengthValidator.getMinimum();
      } else if (validator instanceof RegexValidator) {
        RegexValidator regexValidator = (RegexValidator) validator;
        pattern = regexValidator.getPattern();
      }
    }
    if (maxLength > 0) {
      writer.writeAttribute(HtmlAttributes.MAXLENGTH, maxLength);
    }
    if (minLength > 0) {
      writer.writeAttribute(HtmlAttributes.MINLENGTH, minLength);
    }
    if (pattern != null) {
      writer.writeAttribute(HtmlAttributes.PATTERN, pattern, true);
    }

    writer.writeCommandMapAttribute(JsonUtils.encode(RenderUtils.getBehaviorCommands(facesContext, input)));

    HtmlRendererUtils.renderFocus(clientId, input.isFocus(), ComponentUtils.isError(input), facesContext, writer);

    String placeholder = input.getPlaceholder();
    if (!disabled && !readonly && StringUtils.isNotBlank(placeholder)) {
      writer.writeAttribute(HtmlAttributes.PLACEHOLDER, placeholder, true);
    }
    String currentValue = RenderUtils.currentValue(input);
    if (currentValue != null) {
      if (ComponentUtils.getDataAttribute(input, "html-editor") != null
          && SanitizeMode.auto == input.getSanitize()) {
        final Sanitizer sanitizer = TobagoConfig.getInstance(facesContext).getSanitizer();
        currentValue = sanitizer.sanitize(currentValue);
      }
      // this is because browsers eat the first CR+LF of <textarea>
      if (currentValue.startsWith("\r\n")) {
        currentValue = "\r\n" + currentValue;
      } else if (currentValue.startsWith("\n")) {
        currentValue = "\n" + currentValue;
      } else if (currentValue.startsWith("\r")) {
        currentValue = "\r" + currentValue;
      }
      writer.writeText(currentValue);
    }
  }

  @Override
  protected void encodeEndField(FacesContext facesContext, UIComponent component) throws IOException {
    final TobagoResponseWriter writer = getResponseWriter(facesContext);
    writer.endElement(HtmlElements.TEXTAREA);
  }
}
