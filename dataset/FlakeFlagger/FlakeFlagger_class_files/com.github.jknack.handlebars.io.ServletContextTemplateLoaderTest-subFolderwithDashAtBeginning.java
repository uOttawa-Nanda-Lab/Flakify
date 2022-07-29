/**
 * Copyright (c) 2012 Edgar Espina
 *
 * This file is part of Handlebars.java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jknack.handlebars.io;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.github.jknack.handlebars.TemplateLoader;

public class ServletContextTemplateLoaderTest {

  @Test
  public void subFolderwithDashAtBeginning() throws IOException {
    InputStream is = createMock(InputStream.class);
    is.close();
    expectLastCall();

    ServletContext servletContext = createMock(ServletContext.class);
    expect(servletContext.getResourceAsStream("/mustache/specs/comments.yml")).andReturn(is);

    replay(servletContext, is);

    TemplateLoader locator = new ServletContextTemplateLoader(servletContext);
    locator.setSuffix(".yml");
    Reader reader = locator.load(URI.create("/mustache/specs/comments"));
    assertNotNull(reader);

    IOUtils.closeQuietly(reader);

    verify(servletContext, is);
  }
}
