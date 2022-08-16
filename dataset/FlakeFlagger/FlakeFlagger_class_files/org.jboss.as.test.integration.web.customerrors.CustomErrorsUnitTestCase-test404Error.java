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
package org.jboss.as.test.integration.web.customerrors;

import static org.junit.Assert.assertTrue;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests of custom error forwarding
 *
 * @author Scott.Stark@jboss.org
 * @author lbarreiro@redhat.com
 */
@RunWith(Arquillian.class)
@RunAsClient
public class CustomErrorsUnitTestCase {

    private static Logger log = Logger.getLogger(CustomErrorsUnitTestCase.class);

    /**
	 * Test that the custom 404 error page is seen
	 * @throws Exception
	 */@Test @OperateOnDeployment("error-producer.war") public void test404Error(@ArquillianResource(ErrorGeneratorServlet.class) URL baseURL) throws Exception{int errorCode=HttpURLConnection.HTTP_NOT_FOUND;URL url=new URL(baseURL + "/ErrorGeneratorServlet?errorCode=" + errorCode);testURL(url,errorCode,"404.jsp",null);}

    private void testURL(URL url, int expectedCode, String expectedPage, String expectedError) throws Exception {
        HttpGet httpget = new HttpGet(url.toURI());
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            log.trace("executing request" + httpget.getRequestLine());
            HttpResponse response = httpClient.execute(httpget);

            int statusCode = response.getStatusLine().getStatusCode();
            Header page = response.getFirstHeader("X-CustomErrorPage");
            Header error = response.getFirstHeader("X-ExceptionType");

            assertTrue("Wrong response code: " + statusCode, statusCode == expectedCode);
            if (expectedPage != null) {
                assertTrue("X-CustomErrorPage(" + page + ") is " + expectedPage, page.getValue().equals(expectedPage));
            }
            if (expectedError != null) {
                assertTrue("X-ExceptionType(" + error + ") is " + expectedError, error.getValue().equals(expectedError));
            }
        }
    }
}
