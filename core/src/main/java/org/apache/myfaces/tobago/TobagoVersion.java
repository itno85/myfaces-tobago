package org.apache.myfaces.tobago;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class TobagoVersion {


  private static final Log LOG = LogFactory.getLog(TobagoVersion.class);

  private String name;

  public TobagoVersion() {
    InputStream pom = null;
    try {
      pom = getClass().getClassLoader().getResourceAsStream(
          "META-INF/maven/org.apache.myfaces.tobago/tobago-core/pom.properties");
      Properties properties = new Properties();
      properties.load(pom);
      name = properties.getProperty("version");
    } catch (IOException e) {
      LOG.warn("No version info found.", e);
    } finally {
      IOUtils.closeQuietly(pom);
    }
  }

  public String getName() {
    return name;
  }
}
