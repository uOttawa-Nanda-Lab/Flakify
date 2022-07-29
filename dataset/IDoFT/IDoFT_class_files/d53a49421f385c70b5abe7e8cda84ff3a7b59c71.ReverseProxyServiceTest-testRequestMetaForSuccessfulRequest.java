package com.spinn3r.noxy.reverse;

import com.codahale.metrics.servlets.PingServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.spinn3r.artemis.corpus.test.CorporaAsserter;
import com.spinn3r.artemis.http.ServerBuilder;
import com.spinn3r.artemis.http.servlets.RequestMeta;
import com.spinn3r.artemis.http.servlets.RequestMetaServlet;
import com.spinn3r.artemis.init.BaseLauncherTest;
import com.spinn3r.artemis.init.MockHostnameService;
import com.spinn3r.artemis.init.MockVersionService;
import com.spinn3r.artemis.logging.init.ConsoleLoggingService;
import com.spinn3r.artemis.metrics.init.MetricsService;
import com.spinn3r.artemis.network.NetworkException;
import com.spinn3r.artemis.network.builder.HttpRequest;
import com.spinn3r.artemis.network.builder.HttpRequestBuilder;
import com.spinn3r.artemis.network.builder.proxies.ProxyReferences;
import com.spinn3r.artemis.network.init.DirectNetworkService;
import com.spinn3r.metrics.kairosdb.TaggedMetrics;
import com.spinn3r.noxy.discovery.support.init.DiscoveryListenerSupportService;
import com.spinn3r.noxy.reverse.init.ListenerPorts;
import com.spinn3r.noxy.reverse.init.ReverseProxyService;
import com.spinn3r.noxy.reverse.meta.ListenerMeta;
import com.spinn3r.noxy.reverse.meta.ListenerMetaIndex;
import com.spinn3r.noxy.reverse.meta.OnlineServerMetaIndexProvider;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static com.jayway.awaitility.Awaitility.*;
import static org.junit.Assert.*;

/**
 *
 */
public class ReverseProxyServiceTest extends BaseLauncherTest {

    @Inject
    HttpRequestBuilder httpRequestBuilder;

    @Inject
    RequestMetaServlet requestMetaServlet;

    @Inject
    PingServlet pingServlet;

    @Inject
    Provider<ListenerMetaIndex> listenerMetaIndexProvider;

    @Inject
    Provider<ListenerPorts> listenerPortsProvider;

    @Inject
    TaggedMetrics taggedMetrics;

    CorporaAsserter corporaAsserter = new CorporaAsserter( getClass() );

    Map<String, Server> httpDaemonMap = new HashMap<>();

    @Override
    @Before
    public void setUp() throws Exception {

        super.setUp( MockHostnameService.class,
                     MockVersionService.class,
                     MetricsService.class,
                     DirectNetworkService.class,
                     ConsoleLoggingService.class,
                     DiscoveryListenerSupportService.class,
                     ReverseProxyService.class );

        int startingPort = 1880;
        int nrHttpDaemons = 3;

        for (int i = 0; i < nrHttpDaemons; i++) {

            int port = startingPort + i;

            ServerBuilder serverBuilder = new ServerBuilder();

            Server server =
              serverBuilder
                .setPort( port )
                .setMaxThreads( 10 )
                .setUseLocalhost( true )
                .addServlet( "/ping", pingServlet )
                .addServlet( "/request-meta", requestMetaServlet )
                .build();

            server.start();

            httpDaemonMap.put( ":" + port, server );

        }

    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();

        for (Server server : httpDaemonMap.values()) {
            server.stop();
        }

    }

    @Test
    public void testBasicRequests() throws Exception {

        ListenerMeta listenerMeta = listenerMetaIndexProvider.get().getListenerMetas().get( 0 );

        OnlineServerMetaIndexProvider onlineServerMetaIndexProvider = listenerMeta.getOnlineServerMetaIndexProvider();

        await().until( () -> {
            assertThat( onlineServerMetaIndexProvider.get().getBalancer().size(), equalTo( 3 ) );
        } );

        String content = fetch( "http://example.com/ping" );
        assertThat( content, containsString( "pong" ) );

        // now stop all the daemons to make sure things go offline.

        for (Server server : httpDaemonMap.values()) {
            server.stop();
        }

        await().until( () -> {
            assertThat( onlineServerMetaIndexProvider.get().getBalancer().size(), equalTo( 0 ) );
        } );

        // now start them again to make sure they come back online.

        for (Server server : httpDaemonMap.values()) {
            server.start();
        }

        await().until( () -> {
            assertThat( onlineServerMetaIndexProvider.get().getBalancer().size(), equalTo( 3 ) );
        } );

    }


    @Test
    public void testRequestMetaForSuccessfulRequest() throws Exception {

        ListenerMeta listenerMeta = listenerMetaIndexProvider.get().getListenerMetas().get( 0 );

        OnlineServerMetaIndexProvider onlineServerMetaIndexProvider = listenerMeta.getOnlineServerMetaIndexProvider();

        await().until( () -> {
            assertThat( onlineServerMetaIndexProvider.get().getBalancer().size(), equalTo( 3 ) );
        } );

        String content = fetch( "http://example.com/request-meta" );

        RequestMeta requestMeta = RequestMeta.fromJSON(content);
        requestMeta.getHeaders().remove( "Via" );

        //Those headers are sometimes missing in the example.com response
        requestMeta.getHeaders().remove( "Cache-Control" );
        requestMeta.getHeaders().remove( "Accept" );
        requestMeta.getHeaders().remove( "Pragma" );

        assertEquals( "foo", requestMeta.getHeaders().get( "X-foo" ) );

        content = requestMeta.toJSON();

        corporaAsserter.assertEquals( "testRequestMetaForSuccessfulRequest", content );

    }

    @Test
    public void testVerifyBadGatewayWithNoBackendServers() throws Exception {

        ListenerMeta listenerMeta = listenerMetaIndexProvider.get().getListenerMetas().get( 0 );

        OnlineServerMetaIndexProvider onlineServerMetaIndexProvider = listenerMeta.getOnlineServerMetaIndexProvider();

        for (Server server : httpDaemonMap.values()) {
            server.stop();
        }

        await().until( () -> {
            assertThat( onlineServerMetaIndexProvider.get().getBalancer().size(), equalTo( 0 ) );
        } );

        // the backend servers should be down now so we should get a 502.
        assertEquals( 502, status( "http://example.com/ping" ) );

    }

    private String fetch( String link ) throws NetworkException {

        int port = listenerPortsProvider.get().getPort( "default" );

        String proxyURL = String.format( "http://localhost:%s", port );

        return httpRequestBuilder
                 .get( link )
                 .withProxy(ProxyReferences.create(proxyURL ) )
                 .execute()
                 .getContentWithEncoding();

    }

    // return the status code of the request
    private int status( String link ) {

        try {

            int port = listenerPortsProvider.get().getPort( "default" );

            String proxyURL = String.format( "http://localhost:%s", port );

            HttpRequest httpRequest =
              httpRequestBuilder
                .get( link )
                .withProxy(ProxyReferences.create(proxyURL ) )
                .execute();

            httpRequest.connect();

            return httpRequest.getResponseCode();

        } catch (NetworkException e) {
            return e.getResponseCode();
        }


    }

}