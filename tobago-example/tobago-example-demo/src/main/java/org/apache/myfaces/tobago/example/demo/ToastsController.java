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

package org.apache.myfaces.tobago.example.demo;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.apache.myfaces.tobago.application.Toast;
import org.apache.myfaces.tobago.util.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Named
@SessionScoped
public class ToastsController implements Serializable {

  private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private final List<ToastFacesMessage> toastFacesMessages = new ArrayList<>();
  private final List<Progress> progressBars = new ArrayList<>();
  private final List<CustomToast> customToasts = new ArrayList<>();
  private String severity;
  private ScheduledExecutorService scheduledExecutorService;
  private String customToastMessage;

  public List<ToastFacesMessage> getToastFacesMessages() {
    return toastFacesMessages;
  }

  public void createToastFacesMessage() {
    toastFacesMessages.add(new ToastFacesMessage(severity, "Message text"));
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public List<Progress> getProgressBars() {
    return progressBars;
  }

  public void createProgressBarToast() {
    progressBars.add(new Progress(100));

    if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
      scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

      scheduledExecutorService.scheduleAtFixedRate(() -> {
        for (int i = progressBars.size() - 1; i >= 0; i--) {
          Progress progress = progressBars.get(i);
          if (progress.getValue() >= progress.getMax()) {
            progressBars.remove(i);
          }
        }

        for (Progress progress : progressBars) {
          progress.increase();
        }

        if (progressBars.isEmpty()) {
          scheduledExecutorService.shutdown();
        }
      }, 100, 100, TimeUnit.MILLISECONDS);
    }
  }

  public List<CustomToast> getCustomToasts() {
    return customToasts;
  }

  public void createCustomToast() {
    customToasts.add(new CustomToast(customToastMessage));
  }

  public String getCustomToastMessage() {
    return customToastMessage;
  }

  public void setCustomToastMessage(String customToastMessage) {
    this.customToastMessage = customToastMessage;
  }

  public static class ToastFacesMessage implements Toast {
    private final String id = String.valueOf(UUID.randomUUID());
    private final String severity;
    private final String text;

    public ToastFacesMessage(String severity, String text) {
      this.severity = severity;
      this.text = text;
    }

    @Override
    public String getId() {
      return id;
    }

    public String getSeverityColor() {
      switch (severity) {
        default:
        case "info":
          return "bg-info";
        case "warn":
          return "bg-warning";
        case "error":
        case "fatal":
          return "bg-danger";
      }
    }

    public String getSeverityText() {
      return ResourceUtils.getString(FacesContext.getCurrentInstance(), "severity." + severity);
    }

    public String getText() {
      return text;
    }
  }

  public static class Progress implements Toast {
    private final String id = String.valueOf(UUID.randomUUID());
    private final int max;
    private int value = 0;

    public Progress(int max) {
      this.max = max;
    }

    @Override
    public String getId() {
      return id;
    }

    public void increase() {
      value++;
    }

    public int getMax() {
      return max;
    }

    public int getValue() {
      return value;
    }
  }

  public static class CustomToast implements Toast {
    private final String id = String.valueOf(UUID.randomUUID());
    private final String text;

    public CustomToast(String text) {
      this.text = text;
    }

    @Override
    public String getId() {
      return id;
    }

    public String getText() {
      return text;
    }
  }
}
