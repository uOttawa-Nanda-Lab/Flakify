package com.walmartlabs.ollie.app;

/*-
 * *****
 * Ollie
 * -----
 * Copyright (C) 2018 Takari
 * -----
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
 * =====
 */

import static com.google.common.io.Resources.getResource;
import static io.airlift.http.client.Request.Builder.prepareGet;
import static io.airlift.http.client.StringResponseHandler.createStringResponseHandler;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.walmartlabs.ollie.OllieServer;

import io.airlift.http.client.HttpClientConfig;
import io.airlift.http.client.StringResponseHandler.StringResponse;
import io.airlift.http.client.jetty.JettyHttpClient;

public class OllieSslServerTest {

  protected OllieServer server;

  @Test
  public void validate() throws Exception {
    server = server();
    HttpClientConfig clientConfig =
        new HttpClientConfig()
            .setKeyStorePath(getResource("clientcert-pem/client.pem").getPath())
            .setKeyStorePassword("ollie")
            .setTrustStorePath(getResource("clientcert-pem/ca.crt").getPath());
    assertClientCertificateRequest(clientConfig);
    server.stop();
  }

  protected static OllieServer server() {
    OllieServer server =
        OllieServer.builder()
            .port(0)
            .name("testsslserver")
            .sslEnabled(true)
            .keystorePath(getResource("clientcert-pem/server.pem").getPath())
            .keystorePassword("ollie")
            .truststorePath(getResource("clientcert-pem/ca.crt").getPath())
            .packageToScan("com.walmartlabs.ollie.app")
            .serve("/ssl")
            .with(testServlet())
            .build();
    server.start();
    return server;
  }

  protected String url(String path) {
    int port = server.port();
    return String.format("https://localhost:%s%s", port, path);
  }

  // https://stackoverflow.com/questions/24351472/getattributejavax-servlet-request-x509certificate-not-set-spring-cxf-jettaay
  // https://stackoverflow.com/questions/34486300/javax-servlet-request-x509certificate-request-attribute-does-not-return-ca-cer
  // https://stackoverflow.com/questions/20056304/in-the-jetty-server-how-can-i-obtain-the-client-certificate-used-when-client-aut
  // ^^^ this was the issue
  private void assertClientCertificateRequest(HttpClientConfig clientConfig) throws Exception {
    try (JettyHttpClient httpClient = new JettyHttpClient(clientConfig)) {
      StringResponse response =
          httpClient.execute(
              prepareGet().setUri(new URI(url("/ssl"))).build(), createStringResponseHandler());
      assertEquals(response.getStatusCode(), HttpServletResponse.SC_OK);
      assertEquals(response.getBody(), "CN=testing,OU=Client,O=Ollie,L=Sunnyvale,ST=CA,C=US");
    }
  }

  private static HttpServlet testServlet() {
    return new HttpServlet() {
      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
        X509Certificate[] certs =
            (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
        if ((certs == null) || (certs.length == 0)) {
          throw new RuntimeException("No client certificate");
        }
        if (certs.length > 1) {
          throw new RuntimeException("Received multiple client certificates");
        }
        X509Certificate cert = certs[0];
        response.getWriter().write(cert.getSubjectX500Principal().getName());
        response.setStatus(HttpServletResponse.SC_OK);
      }
    };
  }

  public static void main(String[] args) {
    OllieServer server = server();
  }
}
