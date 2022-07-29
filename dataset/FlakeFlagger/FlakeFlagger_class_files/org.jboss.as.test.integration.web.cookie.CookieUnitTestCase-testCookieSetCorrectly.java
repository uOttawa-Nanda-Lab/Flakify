/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.test.integration.web.cookie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test case for cookie
 *
 * @author prabhat.jha@jboss.com
 * @author lbarreiro@redhat.com
 */
@RunWith(Arquillian.class)
@RunAsClient
public class CookieUnitTestCase {

    protected static Logger log = Logger.getLogger(CookieUnitTestCase.class);

    protected static String[] cookieNames = {"simpleCookie", "withSpace", "commented", "expired"};

    protected static final long fiveSeconds = 5000;

    @ArquillianResource(CookieServlet.class)
    protected URL cookieURL;

    @ArquillianResource(CookieReadServlet.class)
    protected URL cookieReadURL;

    @Test public void testCookieSetCorrectly() throws Exception{log.debug("testCookieSetCorrectly()");try (CloseableHttpClient httpclient=HttpClients.createDefault()){HttpResponse response=httpclient.execute(new HttpGet(cookieReadURL.toURI() + "CookieReadServlet"));if (response.getEntity() != null){response.getEntity().getContent().close();}log.debug("Sending request with cookie");response=httpclient.execute(new HttpPost(cookieReadURL.toURI() + "CookieReadServlet"));} }

    protected boolean checkNoExpiredCookie(List<Cookie> cookies) {
        for (Cookie cookie : cookies) { if (cookie.getName().equals("expired")) { return false; } }
        return true;
    }
}
