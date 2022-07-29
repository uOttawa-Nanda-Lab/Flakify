/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: MIT-0
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.amazon.hub.counter;

import com.amazon.hub.counter.entities.*;
import com.amazon.hub.counter.login.ClientCredentials;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.junit.jupiter.api.Assertions.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.JSONException;

import com.github.tomakehurst.wiremock.http.Fault;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AmazonHubCounterFeedAPITest {

    private static final Integer PORT = 8090;
    private static final String MOCK_API_ENDPOINT = "http://localhost:" + PORT;
    private static final String MOCK_AUTH_ENDPOINT = "http://localhost:" + PORT + "/auth/o2/token";
    WireMockServer wireMockServer;

    ClientCredentials clientCredentials = ClientCredentials.builder()
            .clientId("client_id")
            .clientSecret("client_secret")
            .build();

    private AmazonHubCounterFeedAPI api = new AmazonHubCounterFeedAPI(clientCredentials, MOCK_API_ENDPOINT, MOCK_AUTH_ENDPOINT);

    @BeforeEach
    public void startWireMock() {
        // Initialize WireMock
        wireMockServer = new WireMockServer(PORT);
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());

    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Successful login.")
    public void getAccessTokenTest() {

        // Create WireMock stub
        stubFor(
                WireMock.post(urlEqualTo("/auth/o2/token"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("auth/login-ok-response.json")));

        String accessToken = this.api.getAccessToken();

        assertEquals("Atc|MQEBIOIv_mqVgRWTTMC_DzCa42aptNkl3Vhkh0I0aRR0Xo_JpZMKT7d0BVdHE02uiktSxKZaQ6DS-iyIhHciDCzH6ERhclDnECBQoOrOf1skG4Hmsanhy3WQNAzqebGdrWHIm-1UkcHo9pwOPag9BFWl1bHmqVAj725TFi4yd369TEx5Ss-cSJtpzLsqe4NqEUnuCjrIhr4Ji_Ec7oY9i_F9it01jbUbWxf_bp1iCtPvkEy0dnzVBv28kRuwZIsgqm-a44V5gIbn0CAWLA9HidD71g0pLHyzQjLLZomQQA9QWs5AWQ", accessToken);
    }

    @Test
    @DisplayName("Failed login.")
    public void getAccessTokenErrorTest() {

        // Create WireMock stub
        stubFor(
                WireMock.post(urlEqualTo("/auth/o2/token"))
                        .willReturn(aResponse()
                                .withStatus(401)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("auth/login-error-response.json")));

        assertThrows(RuntimeException.class, () -> {
            this.api.getAccessToken();
        });
    }

    @Test
    @DisplayName("Successful getFeeds()")
    public void getFeedsTest() {

        // Create WireMock stub
        stubFor(
                WireMock.get(urlEqualTo("/v1/feeds"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getFeeds-ok-response.json")));

        FeedsResponse feeds = this.api.getFeeds(null, null, "accessToken");

        // Check nextOffset is null
        assertNull(feeds.getNextOffset());

        // Check two feeds were returned
        assertEquals(2, feeds.getRecords().length);

        // Check properties for feed 1
        assertEquals("9c3a0529-37e6-40dd-8b48-d5w1fgojr8mq", feeds.getRecords()[0].getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feeds.getRecords()[0].getClientId());
        assertEquals(116, (int) feeds.getRecords()[0].getSequenceNumber());
        assertEquals("Completed", feeds.getRecords()[0].getStatus());
        assertEquals(1, feeds.getRecords()[0].getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.17498d12-1bcc-4aab-93cc-kf862mc1xepr.YETMV256LUIMLY", feeds.getRecords()[0].getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feeds.getRecords()[0].getInputDocuments()[0].getDocumentType());
        assertEquals(1, feeds.getRecords()[0].getOutputDocuments().length);
        assertEquals("amzn1.tortuga.3.b6cbeec4-4e57-40c9-80dd-7a5tpm2u5bv3.1CA4VNP36KRNVM", feeds.getRecords()[0].getOutputDocuments()[0].getDocumentId());
        assertEquals("OutputDocument", feeds.getRecords()[0].getOutputDocuments()[0].getDocumentType());
        assertEquals(1563196655781L, (long) feeds.getRecords()[0].getCreateDate());

        // Check properties for feed 2
        assertEquals("9ee9766c-6c3e-4f83-ae9f-cbaozo615w5w", feeds.getRecords()[1].getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feeds.getRecords()[1].getClientId());
        assertEquals(115, (int) feeds.getRecords()[1].getSequenceNumber());
        assertEquals("Completed", feeds.getRecords()[1].getStatus());
        assertEquals(1, feeds.getRecords()[1].getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.385db5f6-1fc2-404f-8d7a-911e0haa5uni.5JV9DULHXAZPLC", feeds.getRecords()[1].getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feeds.getRecords()[1].getInputDocuments()[0].getDocumentType());
        assertEquals(1, feeds.getRecords()[1].getOutputDocuments().length);
        assertEquals("amzn1.tortuga.3.1c8c116c-6eb1-4614-a029-a1f8f11d0306.CK246GP2TIBXTJ", feeds.getRecords()[1].getOutputDocuments()[0].getDocumentId());
        assertEquals("OutputDocument", feeds.getRecords()[1].getOutputDocuments()[0].getDocumentType());
        assertEquals(1563196545566L, (long) feeds.getRecords()[1].getCreateDate());

    }

    @Test
    @DisplayName("Successful getFeeds() with pageSize")
    public void getFeedsWithPageSizeTest() {


        int pageSize = 2;

        String url = "/v1/feeds?pageSize=" + pageSize;

        // Create WireMock stub
        stubFor(
                WireMock.get(urlEqualTo(url))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getFeeds-with-pageSize-ok-response.json")));

        FeedsResponse feeds = this.api.getFeeds(null, 2, "accessToken");

        // Check nextOffset
        assertEquals("76c960fa-9bf6-4204-be66-63477074032d:34032", feeds.getNextOffset());

        // Check two feeds were returned
        assertEquals(pageSize, feeds.getRecords().length);

        // Check properties for feed 1
        assertEquals("c3b16c22-8083-45b6-9327-d5w1fgojr8mq", feeds.getRecords()[0].getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feeds.getRecords()[0].getClientId());
        assertEquals(34053, (int) feeds.getRecords()[0].getSequenceNumber());
        assertEquals("Completed", feeds.getRecords()[0].getStatus());
        assertEquals(1, feeds.getRecords()[0].getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.17498d12-1bcc-4aab-93cc-kf862mc1xepr.YETMV256LUIMLY", feeds.getRecords()[0].getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feeds.getRecords()[0].getInputDocuments()[0].getDocumentType());
        assertEquals(1, feeds.getRecords()[0].getOutputDocuments().length);
        assertEquals("amzn1.tortuga.3.b6cbeec4-4e57-40c9-80dd-7a5tpm2u5bv3.1CA4VNP36KRNVM", feeds.getRecords()[0].getOutputDocuments()[0].getDocumentId());
        assertEquals("OutputDocument", feeds.getRecords()[0].getOutputDocuments()[0].getDocumentType());
        assertEquals(1565959378294L, (long) feeds.getRecords()[0].getCreateDate());

        // Check properties for feed 2
        assertEquals("76c960fa-9bf6-4204-be66-cbaozo615w5w", feeds.getRecords()[1].getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feeds.getRecords()[1].getClientId());
        assertEquals(34032, (int) feeds.getRecords()[1].getSequenceNumber());
        assertEquals("Failed", feeds.getRecords()[1].getStatus());
        assertEquals(1, feeds.getRecords()[1].getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.385db5f6-1fc2-404f-8d7a-911e0haa5uni.5JV9DULHXAZPLC", feeds.getRecords()[1].getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feeds.getRecords()[1].getInputDocuments()[0].getDocumentType());
        assertEquals(0, feeds.getRecords()[1].getOutputDocuments().length);
        assertEquals(1565952851208L, (long) feeds.getRecords()[1].getCreateDate());

    }

    @Test
    @DisplayName("Successful getFeeds() with nextOffset")
    public void getFeedsWithNextOffsetTest() {


        String nextOffset = "017d3e22-a1ff-47bb-b07f-6c9a741bd8c9:34054";

        String url = "/v1/feeds?nextOffset=" + nextOffset;

        // Create WireMock stub
        stubFor(
                WireMock.get(urlEqualTo(url))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getFeeds-with-pageSize-ok-response.json")));

        FeedsResponse feeds = this.api.getFeeds("017d3e22-a1ff-47bb-b07f-6c9a741bd8c9:34054", null, "accessToken");

        // Check nextOffset
        assertEquals("76c960fa-9bf6-4204-be66-63477074032d:34032", feeds.getNextOffset());

        // Check two feeds were returned
        assertEquals(2, feeds.getRecords().length); // Default will be 50, reducing the response for simplicity

        // Check properties for feed 1
        assertEquals("c3b16c22-8083-45b6-9327-d5w1fgojr8mq", feeds.getRecords()[0].getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feeds.getRecords()[0].getClientId());
        assertEquals(34053, (int) feeds.getRecords()[0].getSequenceNumber());
        assertEquals("Completed", feeds.getRecords()[0].getStatus());
        assertEquals(1, feeds.getRecords()[0].getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.17498d12-1bcc-4aab-93cc-kf862mc1xepr.YETMV256LUIMLY", feeds.getRecords()[0].getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feeds.getRecords()[0].getInputDocuments()[0].getDocumentType());
        assertEquals(1, feeds.getRecords()[0].getOutputDocuments().length);
        assertEquals("amzn1.tortuga.3.b6cbeec4-4e57-40c9-80dd-7a5tpm2u5bv3.1CA4VNP36KRNVM", feeds.getRecords()[0].getOutputDocuments()[0].getDocumentId());
        assertEquals("OutputDocument", feeds.getRecords()[0].getOutputDocuments()[0].getDocumentType());
        assertEquals(1565959378294L, (long) feeds.getRecords()[0].getCreateDate());

        // Check properties for feed 2
        assertEquals("76c960fa-9bf6-4204-be66-cbaozo615w5w", feeds.getRecords()[1].getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feeds.getRecords()[1].getClientId());
        assertEquals(34032, (int) feeds.getRecords()[1].getSequenceNumber());
        assertEquals("Failed", feeds.getRecords()[1].getStatus());
        assertEquals(1, feeds.getRecords()[1].getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.385db5f6-1fc2-404f-8d7a-911e0haa5uni.5JV9DULHXAZPLC", feeds.getRecords()[1].getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feeds.getRecords()[1].getInputDocuments()[0].getDocumentType());
        assertEquals(0, feeds.getRecords()[1].getOutputDocuments().length);
        assertEquals(1565952851208L, (long) feeds.getRecords()[1].getCreateDate());

    }

    @Test
    @DisplayName("Successful getFeeds() with pageSize and nextOffset")
    public void getFeedsWithPageSizeAndNextOffsetTest() {


        int pageSize = 2;
        String nextOffset = "017d3e22-a1ff-47bb-b07f-6c9a741bd8c9:34054";

        String url = "/v1/feeds?pageSize=" + pageSize + "&nextOffset=" + nextOffset;

        // Create WireMock stub
        stubFor(
                WireMock.get(urlEqualTo(url))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getFeeds-with-pageSize-ok-response.json")));

        FeedsResponse feeds = this.api.getFeeds("017d3e22-a1ff-47bb-b07f-6c9a741bd8c9:34054", 2, "accessToken");

        // Check nextOffset
        assertEquals("76c960fa-9bf6-4204-be66-63477074032d:34032", feeds.getNextOffset());

        // Check two feeds were returned
        assertEquals(pageSize, feeds.getRecords().length);

        // Check properties for feed 1
        assertEquals("c3b16c22-8083-45b6-9327-d5w1fgojr8mq", feeds.getRecords()[0].getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feeds.getRecords()[0].getClientId());
        assertEquals(34053, (int) feeds.getRecords()[0].getSequenceNumber());
        assertEquals("Completed", feeds.getRecords()[0].getStatus());
        assertEquals(1, feeds.getRecords()[0].getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.17498d12-1bcc-4aab-93cc-kf862mc1xepr.YETMV256LUIMLY", feeds.getRecords()[0].getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feeds.getRecords()[0].getInputDocuments()[0].getDocumentType());
        assertEquals(1, feeds.getRecords()[0].getOutputDocuments().length);
        assertEquals("amzn1.tortuga.3.b6cbeec4-4e57-40c9-80dd-7a5tpm2u5bv3.1CA4VNP36KRNVM", feeds.getRecords()[0].getOutputDocuments()[0].getDocumentId());
        assertEquals("OutputDocument", feeds.getRecords()[0].getOutputDocuments()[0].getDocumentType());
        assertEquals(1565959378294L, (long) feeds.getRecords()[0].getCreateDate());

        // Check properties for feed 2
        assertEquals("76c960fa-9bf6-4204-be66-cbaozo615w5w", feeds.getRecords()[1].getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feeds.getRecords()[1].getClientId());
        assertEquals(34032, (int) feeds.getRecords()[1].getSequenceNumber());
        assertEquals("Failed", feeds.getRecords()[1].getStatus());
        assertEquals(1, feeds.getRecords()[1].getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.385db5f6-1fc2-404f-8d7a-911e0haa5uni.5JV9DULHXAZPLC", feeds.getRecords()[1].getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feeds.getRecords()[1].getInputDocuments()[0].getDocumentType());
        assertEquals(0, feeds.getRecords()[1].getOutputDocuments().length);
        assertEquals(1565952851208L, (long) feeds.getRecords()[1].getCreateDate());

    }

    @Test
    @DisplayName("Failed getFeeds()")
    public void getFeedsErrorTest() {

        // Create WireMock stub
        stubFor(
                WireMock.get(urlEqualTo("/v1/feeds"))
                        .willReturn(aResponse()
                                .withStatus(403)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getFeeds-error-response.json")));

        assertThrows(RuntimeException.class, () -> {
            this.api.getFeeds(null, null, "accessToken");
        });

    }

    @Test
    @DisplayName("Successful getFeedById({feedId})")
    public void getFeedByIdTest() {

        // Create WireMock stub
        String feedId = "9c3a0529-37e6-40dd-8b48-d5w1fgojr8mq";
        stubFor(
                WireMock.get(urlEqualTo("/v1/feeds/" + feedId))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getFeedById-ok-response.json")));

        Feed feed = this.api.getFeedById(feedId, "accessToken");

        // Check properties for feed
        assertEquals(feedId, feed.getFeedId());
        assertEquals("amzn1.application-oa2-client.akml3ubntle0cn85st2ntwvh0d07pzkn", feed.getClientId());
        assertEquals(116, (int) feed.getSequenceNumber());
        assertEquals("Completed", feed.getStatus());
        assertEquals(1, feed.getInputDocuments().length);
        assertEquals("amzn1.tortuga.3.17498d12-1bcc-4aab-93cc-kf862mc1xepr.YETMV256LUIMLY", feed.getInputDocuments()[0].getDocumentId());
        assertEquals("InputDocument", feed.getInputDocuments()[0].getDocumentType());
        assertEquals(1, feed.getOutputDocuments().length);
        assertEquals("amzn1.tortuga.3.b6cbeec4-4e57-40c9-80dd-7a5tpm2u5bv3.1CA4VNP36KRNVM", feed.getOutputDocuments()[0].getDocumentId());
        assertEquals("OutputDocument", feed.getOutputDocuments()[0].getDocumentType());
        assertEquals(1563196655781L, (long) feed.getCreateDate());
    }

    @Test
    @DisplayName("Error in getFeedById({feedId})")
    public void getFeedByIdErrorTest() {

        // Create WireMock stub
        String feedId = "9c3a0529-37e6-40dd-8b48-d5w1fgojr8mq";
        stubFor(
                WireMock.get(urlEqualTo("/v1/feeds/" + feedId))
                        .willReturn(aResponse()
                                .withStatus(403)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getFeedById-error-response.json")));

        assertThrows(RuntimeException.class, () -> {
            this.api.getFeedById(feedId, "accessToken");
        });

    }

    @Test
    @DisplayName("Successful getOutputDocument({feedId}, {documentId})")
    public void getOutputDocumentOkTest() {

        // Create WireMock stub
        String feedId = "9c3a0529-37e6-40dd-8b48-d5w1fgojr8mq";
        String documentId = "amzn1.tortuga.3.b6cbeec4-4e57-40c9-80dd-7a5tpm2u5bv3.1CA4VNP36KRNVM";
        stubFor(
                WireMock.get(urlEqualTo("/v1/feeds/" + feedId + "/documents/" + documentId))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getOutputDocument-ok-response.json")));

        OutputDocument doc = this.api.getOutputDocument(feedId, documentId,"accessToken");

        assertEquals(feedId, doc.getFeedId());
        assertEquals(1, (int) doc.getNoOfAccessPointsProcessed());
        assertEquals(1, (int) doc.getNoOfAccessPointsSuccessfullyProcessed());
        assertEquals(0, (int) doc.getNoOfAccessPointsFailedToProcess());
        assertArrayEquals(new AccessPointProcessingError[0], doc.getFailedAccessPointProcessingDetails());
    }

    @Test
    @DisplayName("Output Document with errors in getOutputDocument({feedId}, {documentId})")
    public void getOutputDocumentWithErrorsTest() {

        // Create WireMock stub
        String feedId = "9ee9766c-6c3e-4f83-ae9f-cbaozo615w5w";
        String documentId = "amzn1.tortuga.3.1c8c116c-6eb1-4614-a029-a1f8f11d0306.CK246GP2TIBXTJ";
        stubFor(
                WireMock.get(urlEqualTo("/v1/feeds/" + feedId + "/documents/" + documentId))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getOutputDocument-with-errors-response.json")));

        OutputDocument doc = this.api.getOutputDocument(feedId, documentId,"accessToken");

        assertEquals(feedId, doc.getFeedId());
        assertEquals(1, (int) doc.getNoOfAccessPointsProcessed());
        assertEquals(0, (int) doc.getNoOfAccessPointsSuccessfullyProcessed());
        assertEquals(1, (int) doc.getNoOfAccessPointsFailedToProcess());
        assertEquals(1, doc.getFailedAccessPointProcessingDetails().length);
        assertEquals(1, (int) doc.getFailedAccessPointProcessingDetails()[0].getIndexNumber());
        assertEquals("TEST-STORE-ID", doc.getFailedAccessPointProcessingDetails()[0].getAccessPointId());
        assertEquals("FAILURE", doc.getFailedAccessPointProcessingDetails()[0].getResultStatus());
        assertEquals(1, doc.getFailedAccessPointProcessingDetails()[0].getErrors().length);
        assertEquals("STORE_CREATION_FAILURE", doc.getFailedAccessPointProcessingDetails()[0].getErrors()[0].getErrorCode());
        assertEquals("[STORE_CREATION_FAILURE] Error occurred while creation of store", doc.getFailedAccessPointProcessingDetails()[0].getErrors()[0].getErrorMessage());
    }

    @Test
    @DisplayName("Error in getOutputDocument({feedId}, {documentId})")
    public void getOutputDocumentErrorTest() {

        // Create WireMock stub
        String feedId = "9ee9766c-6c3e-4f83-ae9f-cbaozo615w5w";
        String documentId = "amzn1.tortuga.3.1c8c116c-6eb1-4614-a029-a1f8f11d0306.CK246GP2TIBXTJ";
        stubFor(
                WireMock.get(urlEqualTo("/v1/feeds/" + feedId + "/documents/" + documentId))
                        .willReturn(aResponse()
                                .withStatus(403)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getOutputDocument-error-response.json")));

        assertThrows(RuntimeException.class, () -> {
            this.api.getOutputDocument(feedId, documentId, "accessToken");
        });

    }

    @Test
    @DisplayName("Successful getInputDocument({feedId}, {documentId})")
    public void getInputDocumentOkTest() {

        // Create WireMock stub
        String feedId = "9c3a0529-37e6-40dd-8b48-d5w1fgojr8mq";
        String documentId = "amzn1.tortuga.3.17498d12-1bcc-4aab-93cc-kf862mc1xepr.YETMV256LUIMLY";
        stubFor(
                WireMock.get(urlEqualTo("/v1/feeds/" + feedId + "/documents/" + documentId))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/getInputDocument-ok" +
                                        "-response.json")));

        AccessPointsFeedRequest req = this.api.getInputDocument(feedId, documentId,"accessToken");

        assertEquals("UAT-NA-PROD-AMAZON-US", req.getAccessPoints()[0].getAccessPointId());
        assertEquals("Amazon Hub Counter - Amazon US HQ", req.getAccessPoints()[0].getAccessPointName());
        assertEquals(true, req.getAccessPoints()[0].getIsActive());
        assertEquals("America/Los_Angeles", req.getAccessPoints()[0].getTimeZone());
        // address
        assertEquals("1918 8th Ave, Seattle, WA 98101, USA", req.getAccessPoints()[0].getAddress().getAddressFieldOne());
        assertNull(req.getAccessPoints()[0].getAddress().getAddressFieldTwo());
        assertNull(req.getAccessPoints()[0].getAddress().getAddressFieldThree());
        assertEquals("Seattle", req.getAccessPoints()[0].getAddress().getCity());
        assertEquals("Washington", req.getAccessPoints()[0].getAddress().getRegion());
        assertNull(req.getAccessPoints()[0].getAddress().getDistrict());
        assertEquals("US", req.getAccessPoints()[0].getAddress().getCountryCode());
        assertEquals("47.615564", req.getAccessPoints()[0].getAddress().getLatitude());
        assertEquals("-122.335819", req.getAccessPoints()[0].getAddress().getLongitude());
        assertEquals("2199-12-31", req.getAccessPoints()[0].getTerminationDate());
        assertEquals("PICK_UP", req.getAccessPoints()[0].getCapabilities()[0]);
        assertEquals("DROP_OFF", req.getAccessPoints()[0].getCapabilities()[1]);
        //capacity
        assertNull(req.getAccessPoints()[0].getCapacity());
        // standardHours
        assertEquals("MONDAY", req.getAccessPoints()[0].getStandardHoursList()[0].getDay());
        assertEquals("08:30:00", req.getAccessPoints()[0].getStandardHoursList()[0].getOpeningTime());
        assertEquals("20:00:00", req.getAccessPoints()[0].getStandardHoursList()[0].getClosingTime());
        assertEquals("TUESDAY", req.getAccessPoints()[0].getStandardHoursList()[1].getDay());
        assertEquals("08:30:00", req.getAccessPoints()[0].getStandardHoursList()[1].getOpeningTime());
        assertEquals("20:00:00", req.getAccessPoints()[0].getStandardHoursList()[1].getClosingTime());
        assertEquals("WEDNESDAY", req.getAccessPoints()[0].getStandardHoursList()[2].getDay());
        assertEquals("08:30:00", req.getAccessPoints()[0].getStandardHoursList()[2].getOpeningTime());
        assertEquals("20:00:00", req.getAccessPoints()[0].getStandardHoursList()[2].getClosingTime());
        assertEquals("THURSDAY", req.getAccessPoints()[0].getStandardHoursList()[3].getDay());
        assertEquals("08:30:00", req.getAccessPoints()[0].getStandardHoursList()[3].getOpeningTime());
        assertEquals("20:00:00", req.getAccessPoints()[0].getStandardHoursList()[3].getClosingTime());
        assertEquals("FRIDAY", req.getAccessPoints()[0].getStandardHoursList()[4].getDay());
        assertEquals("08:30:00", req.getAccessPoints()[0].getStandardHoursList()[4].getOpeningTime());
        assertEquals("20:00:00", req.getAccessPoints()[0].getStandardHoursList()[4].getClosingTime());
        assertEquals("SATURDAY", req.getAccessPoints()[0].getStandardHoursList()[5].getDay());
        assertEquals("08:30:00", req.getAccessPoints()[0].getStandardHoursList()[5].getOpeningTime());
        assertEquals("20:00:00", req.getAccessPoints()[0].getStandardHoursList()[5].getClosingTime());
        assertEquals("12:00:00", req.getAccessPoints()[0].getStandardHoursList()[5].getMidDayClosures()[0].getStartTime());
        assertEquals("13:00:00", req.getAccessPoints()[0].getStandardHoursList()[5].getMidDayClosures()[0].getEndTime());

        assertEquals("2038-01-19", req.getAccessPoints()[0].getExceptionalClosures()[0].getStartDateTime());
        assertEquals("2038-01-20", req.getAccessPoints()[0].getExceptionalClosures()[0].getEndDateTime());
        assertEquals("store-id@example.com", req.getAccessPoints()[0].getCommunicationDetails().getEmailId());
        assertEquals("00 1 206-922-0880", req.getAccessPoints()[0].getCommunicationDetails().getPhoneNumber());
        assertNull(req.getAccessPoints()[0].getCommunicationDetails().getFaxPhone());

    }

    @Test
    @DisplayName("Successful Feed JSON serialization")
    public void postFeedSerializationTest() throws IOException {
        // Create an array of Access Points
        AccessPoint[] accessPoints = new AccessPoint[1];

        // Create Address
        Address address = Address.builder()
                .addressFieldOne("1918 8th Ave, Seattle, WA 98101, USA")
                .city("Seattle")
                .postalCode("98101")
                .countryCode("US")
                .latitude("47.615564")
                .longitude("-122.335819")
                .build();

        // Create Capabilities
        String[] capabilities = {"PICK_UP", "DROP_OFF"};

        // Create an Exceptional Closure
        ExceptionalClosure[] exceptionalClosures = new ExceptionalClosure[1];
        exceptionalClosures[0] = ExceptionalClosure.builder()
                .startDateTime("2038-01-19")
                .endDateTime("2038-01-20")
                .build();

        // Create the Communication Details
        CommunicationDetails communicationDetails = CommunicationDetails.builder()
                .phoneNumber("00 1 206-922-0880")
                .emailId("store-id@example.com")
                .build();

        // Create Standard Hours
        StandardHours[] standardHours = new StandardHours[6];

        // Monday
        standardHours[0] = StandardHours.builder()
                .day("MONDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Tuesday
        standardHours[1] = StandardHours.builder()
                .day("TUESDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Wednesday
        standardHours[2] = StandardHours.builder()
                .day("WEDNESDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Thursday
        standardHours[3] = StandardHours.builder()
                .day("THURSDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Friday
        standardHours[4] = StandardHours.builder()
                .day("FRIDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Saturday
        // Create a MidDay Closure
        MidDayClosure[] midDayClosures = new MidDayClosure[1];
        midDayClosures[0] = MidDayClosure.builder()
                .startTime("12:00:00")
                .endTime("13:00:00")
                .build();
        standardHours[5] = StandardHours.builder()
                .day("SATURDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .midDayClosures(midDayClosures)
                .build();

        // Create an Access Point
        accessPoints[0] = AccessPoint.builder()
                .accessPointId("AMAZON-US-HQ")
                .accessPointName("Amazon Hub Counter - Amazon US HQ")
                .isActive(true)
                .isRestrictedAccess(false)
                .timeZone("America/Los_Angeles")
                .address(address)
                .terminationDate("2199-12-31")
                .capabilities(capabilities)
                .standardHoursList(standardHours)
                .exceptionalClosures(exceptionalClosures)
                .communicationDetails(communicationDetails)
                .build();

        AccessPointsFeedRequest accessPointsFeedRequest = AccessPointsFeedRequest.builder()
                .accessPoints(accessPoints)
                .build();

        String feedRequestJson = new String(Files.readAllBytes(
                Paths.get("src/test/resources/__files/feeds/feedRequest.json")
        ));

        try {
                JSONAssert.assertEquals(accessPointsFeedRequest.toJson(), feedRequestJson, false);
        } catch (JSONException e) {
                throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Test
    @DisplayName("Successful postFeed()")
    public void postFeedTest() {

        // Create an array of Access Points
        AccessPoint[] accessPoints = new AccessPoint[1];

        // Create Address
        Address address = Address.builder()
                .addressFieldOne("1918 8th Ave, Seattle, WA 98101, USA")
                .city("Seattle")
                .postalCode("98101")
                .countryCode("US")
                .latitude("47.615564")
                .longitude("-122.335819")
                .build();

        // Create Capabilities
        String[] capabilities = {"PICK_UP", "DROP_OFF"};

        // Create an Exceptional Closure
        ExceptionalClosure[] exceptionalClosures = new ExceptionalClosure[1];
        exceptionalClosures[0] = ExceptionalClosure.builder()
                .startDateTime("2038-01-19")
                .endDateTime("2038-01-20")
                .build();

        // Create the Communication Details
        CommunicationDetails communicationDetails = CommunicationDetails.builder()
                .phoneNumber("00 1 206-922-0880")
                .emailId("store-id@example.com")
                .build();

        // Create Standard Hours
        StandardHours[] standardHours = new StandardHours[6];

        // Monday
        standardHours[0] = StandardHours.builder()
                .day("MONDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Tuesday
        standardHours[1] = StandardHours.builder()
                .day("TUESDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Wednesday
        standardHours[2] = StandardHours.builder()
                .day("WEDNESDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Thursday
        standardHours[3] = StandardHours.builder()
                .day("THURSDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Friday
        standardHours[4] = StandardHours.builder()
                .day("FRIDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();
        // Saturday
        // Create a MidDay Closure
        MidDayClosure[] midDayClosures = new MidDayClosure[1];
        midDayClosures[0] = MidDayClosure.builder()
                .startTime("12:00:00")
                .endTime("13:00:00")
                .build();
        standardHours[5] = StandardHours.builder()
                .day("SATURDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .midDayClosures(midDayClosures)
                .build();

        // Create an Access Point
        accessPoints[0] = AccessPoint.builder()
                .accessPointId("AMAZON-US-HQ")
                .accessPointName("Amazon Hub Counter - Amazon US HQ")
                .isActive(true)
                .isRestrictedAccess(false)
                .timeZone("America/Los_Angeles")
                .address(address)
                .terminationDate("2199-12-31")
                .capabilities(capabilities)
                .standardHoursList(standardHours)
                .exceptionalClosures(exceptionalClosures)
                .communicationDetails(communicationDetails)
                .build();

        AccessPointsFeedRequest accessPointsFeedRequest = AccessPointsFeedRequest.builder()
                .accessPoints(accessPoints)
                .build();

        // Create WireMock stub
        stubFor(
                WireMock.post(urlEqualTo("/v1/feeds?feedType=STORE_FEED"))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("content-type", "application/json")
                                .withBodyFile("feeds/postFeed-ok-response.json")));

        String feedId = this.api.postFeed(accessPointsFeedRequest, FeedType.STORE_FEED, "accessToken");

        assertEquals("9c3a0529-37e6-40dd-8b48-d5w1fgojr8mq", feedId);

    }

    @Test
    @DisplayName("Error in postFeed()")
    public void postFeedErrorTest() {

        // Create an array of Access Points
        AccessPoint[] accessPoints = new AccessPoint[1];

        // Create Address
        Address address = Address.builder()
                .addressFieldOne("1918 8th Ave, Seattle, WA 98101, USA")
                .city("Seattle")
                .postalCode("98101")
                .countryCode("US")
                .latitude("47.615564")
                .longitude("-122.335819")
                .build();

        // Create Capabilities
        String[] capabilities = {"PICK_UP", "DROP_OFF"};

        // Create the Communication Details
        CommunicationDetails communicationDetails = CommunicationDetails.builder()
                .phoneNumber("00 1 206-922-0880")
                .emailId("store-id@example.com")
                .build();

        // Create Standard Hours
        StandardHours[] standardHours = new StandardHours[1];

        // Monday
        standardHours[0] = StandardHours.builder()
                .day("MONDAY")
                .openingTime("08:30:00")
                .closingTime("20:00:00")
                .build();

        // Create an Access Point
        accessPoints[0] = AccessPoint.builder()
                .accessPointId("AMAZON-US-HQ")
                .accessPointName("Amazon Hub Counter - Amazon US HQ")
                .isActive(true)
                .timeZone("America/Los_Angeles")
                .address(address)
                .terminationDate("2199-12-31")
                .capabilities(capabilities)
                .standardHoursList(standardHours)
                .communicationDetails(communicationDetails)
                .build();

        AccessPointsFeedRequest accessPointsFeedRequest = AccessPointsFeedRequest.builder()
                .accessPoints(accessPoints)
                .build();

        // Create WireMock stub
        stubFor(
                WireMock.post(urlEqualTo("/v1/feeds?feedType=STORE_FEED"))
                        .willReturn(aResponse()
                                .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        assertThrows(RuntimeException.class, () -> {
            this.api.postFeed(accessPointsFeedRequest, FeedType.STORE_FEED, "accessToken");
        });

    }

}