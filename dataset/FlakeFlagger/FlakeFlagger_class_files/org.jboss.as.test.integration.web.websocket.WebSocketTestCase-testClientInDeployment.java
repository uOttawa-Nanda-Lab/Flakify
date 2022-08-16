package org.jboss.as.test.integration.web.websocket;

import static org.jboss.as.test.shared.integration.ejb.security.PermissionUtils.createPermissionsXmlAsset;

import java.io.IOException;
import java.net.SocketPermission;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.PropertyPermission;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.as.test.shared.TestSuiteEnvironment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Simple smoke test for WebSockets. It tests both basic use-cases - WebSocket client either as a standalone application
 * or as a part of a WAR-deployment.
 *
 * @author Stuart Douglas
 */
@RunWith(Arquillian.class)
public class WebSocketTestCase {

    private static final String CLIENT_STANDALONE = "standalone";
    private static final String CLIENT_IN_DEPLOYMENT = "indeployment";

    @Test @OperateOnDeployment(CLIENT_IN_DEPLOYMENT) public void testClientInDeployment(@ArquillianResource URL webapp) throws Exception{assertWebSocket(webapp);}

    private void assertWebSocket(URL webapp) throws InterruptedException, IOException, DeploymentException, URISyntaxException {
        AnnotatedClient endpoint = new AnnotatedClient();
        WebSocketContainer serverContainer = ContainerProvider.getWebSocketContainer();
        try (Session session = serverContainer.connectToServer(endpoint,
                new URI("ws", "", TestSuiteEnvironment.getServerAddress(), TestSuiteEnvironment.getHttpPort(),
                        webapp.getPath() + "websocket/Stuart", "", ""))) {
            Assert.assertEquals("Hello Stuart", endpoint.getMessage());
        }
    }

    /**
     * Creates basic deployment with given name for WebSocket endpoint.
     *
     * @param name deployment name
     * @return Shrinkwrap WebArchive instance
     */
    private static WebArchive createBasicDeployment(String name) {
        return ShrinkWrap.create(WebArchive.class, name + ".war")
                .addClasses(AnnotatedEndpoint.class)
                .addAsManifestResource(new StringAsset("io.undertow.websockets.jsr.UndertowContainerProvider"),
                        "services/javax.websocket.ContainerProvider");
    }

}
