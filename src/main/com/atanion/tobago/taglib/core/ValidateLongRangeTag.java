/*
 * Copyright (c) 2003 Atanion GmbH, Germany
 * All rights reserved. Created 16.01.2004 11:55:11.
 * $Id$
 */
package com.atanion.tobago.taglib.core;

import javax.faces.validator.LongRangeValidator;
import javax.faces.validator.Validator;
import javax.faces.webapp.ValidatorTag;
import javax.servlet.jsp.JspException;

public class ValidateLongRangeTag extends ValidatorTag {

// ///////////////////////////////////////////// constant

// ///////////////////////////////////////////// attribute

  private String minimum;

  private String maximum;

// ///////////////////////////////////////////// constructor

  public ValidateLongRangeTag() {
    setValidatorId(LongRangeValidator.VALIDATOR_ID);
  }

// ///////////////////////////////////////////// code

  // todo: jsfbeta: implement VB-Expressions
  protected Validator createValidator() throws JspException {
    LongRangeValidator validator = (LongRangeValidator) super.createValidator();
    if (minimum != null) {
      validator.setMinimum(Long.parseLong(minimum));
    }
    if (maximum != null) {
      validator.setMaximum(Long.parseLong(maximum));
    }
    return validator;
  }

// ///////////////////////////////////////////// bean getter + setter

  public void setMinimum(String minimum) {
    this.minimum = minimum;
  }

  public void setMaximum(String maximum) {
    this.maximum = maximum;
  }
}
