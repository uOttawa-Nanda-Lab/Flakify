/*
 * Copyright (c) 2018 Cloudera, Inc. All Rights Reserved.
 *
 * Portions Copyright (c) Copyright 2013-2018 Amazon.com, Inc. or its
 * affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloudera.altus.client;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cloudera.altus.AltusClientException;
import com.cloudera.altus.AltusHTTPException;
import com.cloudera.altus.AltusServiceException;
import com.cloudera.altus.authentication.credentials.BasicAltusCredentials;
import com.cloudera.altus.http.ExponentialBackoffDelayPolicy;
import com.cloudera.altus.http.HttpCodesRetryChecker;
import com.cloudera.altus.http.SimpleRetryHandler;
import com.cloudera.altus.util.AltusSDKTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

import java.time.Duration;
import java.util.Map;

import javax.annotation.Nullable;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.junit.jupiter.api.Test;

public class AltusClientTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  private Response mockResponse(int httpCode, @Nullable String requestId) {
    Response response = mock(Response.class);
    StatusType statusType = mock(StatusType.class);
    doReturn(httpCode).when(statusType).getStatusCode();
    doReturn(Response.Status.Family.familyOf(httpCode))
      .when(statusType).getFamily();
    doReturn(statusType).when(response).getStatusInfo();
    MultivaluedHashMap<String, Object> headers = new MultivaluedHashMap<>();
    if (requestId != null) {
      headers.put(AltusResponse.ALTUS_HEADER_REQUESTID,
                  ImmutableList.of(requestId));
    }
    doReturn(headers).when(response).getHeaders();
    return response;
  }

  private static class TestClient extends AltusClient {
    private int apiCalls = 0;
    private final Response response;

    TestClient(Response response) {
      super(new BasicAltusCredentials("accessKeyID",
                                      AltusSDKTestUtils.getRSAPrivateKey()),
            "endpoint",
            AltusClientConfigurationBuilder.defaultBuilder()
            .withRetryHandler(
              new SimpleRetryHandler(
                new HttpCodesRetryChecker(HttpCodesRetryChecker.DEFAULT_RETRY_CODES),
                new ExponentialBackoffDelayPolicy(1, Duration.ofMillis(1)),
                3))
            .build());
      this.response = checkNotNull(response);
    }

    @Override
    protected Response getAPIResponse(String path, Object body)
        throws AltusServiceException {
      apiCalls++;
      return response;
    }
  }

  public static class TestAltusResponse extends AltusResponse {
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testSuccessfulResponse() {
    Response mockResponse = mockResponse(200, "requestId");
    TestAltusResponse altusResponse = new TestAltusResponse();
    when(mockResponse.readEntity(any(GenericType.class))).thenReturn(
        altusResponse);
    TestClient client = new TestClient(mockResponse);
    TestAltusResponse response =
        client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
    assertEquals("requestId", response.getRequestId());
    assertEquals(1, client.apiCalls);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testNullSuccessfulResponse() {
    Response mockResponse = mockResponse(200, "requestId");
    when(mockResponse.readEntity(any(GenericType.class))).thenReturn(null);
    TestClient client = new TestClient(mockResponse);
    Throwable e = assertThrows(AltusHTTPException.class, () -> {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
    });
    assertEquals(String.format("200: Invalid response from server"), e.getMessage());
  }

  @Test
  public void testNonAltusHTTPError() throws Exception {
    String responseBody = Resources.toString(
        Resources.getResource("502.html"), Charsets.UTF_8);
    Response mockResponse = mockResponse(502, null);
    when(mockResponse.readEntity(String.class)).thenReturn(responseBody);
    TestClient client = new TestClient(mockResponse);
    Throwable e = assertThrows(AltusHTTPException.class, () -> {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
    });
    assertEquals(String.format("502: %s", responseBody), e.getMessage());
  }

  @Test
  public void testAltusError() throws Exception {
    Map<String, String> error = Maps.newHashMap();
    error.put("code", "CODE");
    error.put("message", "message!");
    Response mockResponse = mockResponse(500, "requestId");
    when(mockResponse.readEntity(String.class)).thenReturn(
        MAPPER.writeValueAsString(error));
    TestClient client = new TestClient(mockResponse);
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
      fail();
    } catch (AltusServiceException e) {
      assertEquals(1, client.apiCalls);
      assertEquals(500, e.getHttpCode());
      assertEquals(
          "com.cloudera.altus.AltusServiceException: 500: CODE: message! requestId",
          e.getMessage());
      assertEquals("requestId", e.getRequestId());
    }
  }

  @Test
  public void testBadAltusErrors() throws Exception {
    // No body at all.
    Response mockResponse = mockResponse(500, "requestId");
    when(mockResponse.readEntity(String.class)).thenReturn(null);
    TestClient client = new TestClient(mockResponse);
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
      fail();
    } catch (AltusHTTPException e) {
      assertEquals(1, client.apiCalls);
      assertEquals(500, e.getHttpCode());
      assertEquals("500: null", e.getMessage());
    }
    // A non-JSON body.
    when(mockResponse.readEntity(String.class)).thenReturn("NOT JSON!");
    client = new TestClient(mockResponse);
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
      fail();
    } catch (AltusHTTPException e) {
      assertEquals(1, client.apiCalls);
      assertEquals(500, e.getHttpCode());
      assertEquals("500: NOT JSON!", e.getMessage());
    }
    // A JSON body with no content.
    Map<String, String> error = Maps.newHashMap();
    when(mockResponse.readEntity(String.class)).thenReturn(
        MAPPER.writeValueAsString(error));
    client = new TestClient(mockResponse);
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
      fail();
    } catch (AltusHTTPException e) {
      assertEquals(1, client.apiCalls);
      assertEquals(500, e.getHttpCode());
      assertEquals("500: {}", e.getMessage());
    }
    // A JSON body with only the code.
    error.put("code", "CODE");
    when(mockResponse.readEntity(String.class)).thenReturn(
        MAPPER.writeValueAsString(error));
    client = new TestClient(mockResponse);
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
      fail();
    } catch (AltusHTTPException e) {
      assertEquals(1, client.apiCalls);
      assertEquals(500, e.getHttpCode());
      assertEquals("500: " + MAPPER.writeValueAsString(error), e.getMessage());
    }
    // A JSON body with only the message.
    error.clear();
    error.put("message", "MESSAGE!");
    when(mockResponse.readEntity(String.class)).thenReturn(
        MAPPER.writeValueAsString(error));
    client = new TestClient(mockResponse);
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
      fail();
    } catch (AltusHTTPException e) {
      assertEquals(1, client.apiCalls);
      assertEquals(500, e.getHttpCode());
      assertEquals("500: " + MAPPER.writeValueAsString(error), e.getMessage());
    }
    // A JSON error but no request ID.
    mockResponse = mockResponse(500, null);
    error.put("code", "CODE");
    error.put("message", "message!");
    when(mockResponse.readEntity(String.class)).thenReturn(
        MAPPER.writeValueAsString(error));
    client = new TestClient(mockResponse);
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>(){});
      fail();
    } catch (AltusHTTPException e) {
      assertEquals(1, client.apiCalls);
      assertEquals(500, e.getHttpCode());
      assertEquals("500: " + MAPPER.writeValueAsString(error), e.getMessage());
    }
  }

  @Test
  public void testRetries() {
    Response mockResponse = mockResponse(503, null);
    TestClient client = new TestClient(mockResponse);
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>() {});
      fail();
    } catch (AltusHTTPException e) {
      assertEquals(3, client.apiCalls);
      assertEquals(503, e.getHttpCode());
      assertEquals("503: null", e.getMessage());
    }
  }

  @Test
  public void testCallAfterShutdown() {
    AltusClient client = new AltusClient(
        new BasicAltusCredentials("accessKeyID",
            AltusSDKTestUtils.getRSAPrivateKey()),
        "endpoint",
        AltusClientConfigurationBuilder.defaultBuilder().build()) {
    };
    client.shutdown();
    try {
      client.invokeAPI("somePath", "", new GenericType<TestAltusResponse>() {});
      fail();
    } catch (AltusClientException e) {
      assertEquals("Client instance has been closed.", e.getMessage());
    }
  }

  @Test
  public void testShutdownTwice() {
    Response mockResponse = mockResponse(503, null);
    TestClient client = new TestClient(mockResponse);
    client.shutdown();
    client.shutdown();
  }
}
