package org.jboss.as.test.integration.web.handlers;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.FILE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.FILE_HANDLER;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.LOGGER;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.PERIODIC_ROTATING_FILE_HANDLER;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.PROTOCOL;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUBSYSTEM;
import static org.jboss.as.test.integration.management.util.ModelUtil.createOpNode;
import static org.jboss.as.test.shared.integration.ejb.security.PermissionUtils.createPermissionsXmlAsset;

import java.io.File;
import java.net.SocketPermission;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.PropertyPermission;

import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.as.arquillian.api.ServerSetup;
import org.jboss.as.arquillian.container.ManagementClient;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.as.controller.operations.common.Util;
import org.jboss.as.test.integration.management.ManagementOperations;
import org.jboss.as.test.integration.security.common.SecurityTestConstants;
import org.jboss.as.test.integration.security.common.Utils;
import org.jboss.as.test.integration.web.websocket.WebSocketTestCase;
import org.jboss.as.test.shared.ServerReload;
import org.jboss.as.test.shared.SnapshotRestoreSetupTask;
import org.jboss.as.test.shared.TestSuiteEnvironment;
import org.jboss.dmr.ModelNode;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the use of undertow request dumping handler.
 *
 * @author <a href="mailto:jstourac@redhat.com">Jan Stourac</a>
 */
@RunWith(Arquillian.class)
@RunAsClient
@ServerSetup(RequestDumpingHandlerTestCase.RequestDumpingHandlerTestCaseSetupAction.class)
public class RequestDumpingHandlerTestCase {

    public static class RequestDumpingHandlerTestCaseSetupAction extends SnapshotRestoreSetupTask {

        private static String relativeTo;
        private static ModelNode originalValue;

        /** Name of the log file that will be used for testing. */
        private static final String LOG_FILE_PREFIX = "test_server_" + System.currentTimeMillis();
        private static final String LOG_FILE_SUFFIX = ".log";

        // Address to server log setting
        private static final PathAddress aLogAddr = PathAddress.pathAddress().append(SUBSYSTEM, "logging")
                .append(PERIODIC_ROTATING_FILE_HANDLER, "FILE");

        // Address to custom file handler
        private static final String FILE_HANDLER_NAME = "testing-req-dump-handler";
        private static final PathAddress ADDR_FILE_HANDLER = PathAddress.pathAddress().append(SUBSYSTEM, "logging")
                .append(FILE_HANDLER, FILE_HANDLER_NAME);

        // Address to custom logger
        private static final String LOGGER_NAME = "io.undertow.request.dump";
        private static final PathAddress ADDR_LOGGER = PathAddress.pathAddress().append(SUBSYSTEM, "logging")
                .append(LOGGER, LOGGER_NAME);

        private static final File WORK_DIR = new File("https-workdir");
        public static final File SERVER_KEYSTORE_FILE = new File(WORK_DIR, SecurityTestConstants.SERVER_KEYSTORE);
        public static final File SERVER_TRUSTSTORE_FILE = new File(WORK_DIR, SecurityTestConstants.SERVER_TRUSTSTORE);
        public static final File CLIENT_KEYSTORE_FILE = new File(WORK_DIR, SecurityTestConstants.CLIENT_KEYSTORE);
        public static final File CLIENT_TRUSTSTORE_FILE = new File(WORK_DIR, SecurityTestConstants.CLIENT_TRUSTSTORE);
        public static final File UNTRUSTED_KEYSTORE_FILE = new File(WORK_DIR, SecurityTestConstants.UNTRUSTED_KEYSTORE);

        private static final String HTTPS = "https";
        private static final String HTTPS_LISTENER_PATH = "subsystem=undertow/server=default-server/https-listener=" + HTTPS;

        private static final String HTTPS_REALM = "httpsRealm";
        private static final String HTTPS_REALM_PATH = "core-service=management/security-realm=" + HTTPS_REALM;
        private static final String HTTPS_REALM_AUTH_PATH = HTTPS_REALM_PATH + "/authentication=truststore";
        private static final String HTTPS_REALM_SSL_PATH = HTTPS_REALM_PATH + "/server-identity=ssl";

        @Override
        public void doSetup(ManagementClient managementClient, String containerId) throws Exception {
            // Retrieve original path to server log files
            ModelNode op = Util.getReadAttributeOperation(aLogAddr, "file");
            originalValue = ManagementOperations.executeOperation(managementClient.getControllerClient(), op);
            log.debug("Original value: " + originalValue.toString());
            // Retrieve relative path to log files
            relativeTo = originalValue.get("relative-to").asString();

            op = Util.getReadAttributeOperation(PathAddress.pathAddress("path", relativeTo), "path");
            ModelNode logPathModel = ManagementOperations.executeOperation(managementClient.getControllerClient(), op);
            logFilePath = Paths.get(logPathModel.asString() + File.separator + LOG_FILE_PREFIX + LOG_FILE_SUFFIX);

            // Set custom file handler to log dumping requests into separate log file
            ModelNode file = new ModelNode();
            file.get("relative-to").set(relativeTo);
            file.get("path").set(LOG_FILE_PREFIX + LOG_FILE_SUFFIX);
            op = Util.createAddOperation(ADDR_FILE_HANDLER);
            op.get(FILE).set(file);
            ManagementOperations.executeOperation(managementClient.getControllerClient(), op);

            // Set custom logger that uses previous custom file handler for logging
            op = Util.createAddOperation(ADDR_LOGGER);
            LinkedList<ModelNode> handlers = new LinkedList<ModelNode>();
            handlers.add(new ModelNode(FILE_HANDLER_NAME));
            op.get("handlers").set(handlers);
            ManagementOperations.executeOperation(managementClient.getControllerClient(), op);

            // Set HTTPS listener...
            FileUtils.deleteDirectory(WORK_DIR);
            WORK_DIR.mkdirs();
            Utils.createKeyMaterial(WORK_DIR);

            // add new HTTPS_REALM with SSL
            ModelNode operation = createOpNode(HTTPS_REALM_PATH, ModelDescriptionConstants.ADD);
            Utils.applyUpdate(operation, managementClient.getControllerClient());

            operation = createOpNode(HTTPS_REALM_AUTH_PATH, ModelDescriptionConstants.ADD);
            operation.get("keystore-path").set(SERVER_TRUSTSTORE_FILE.getAbsolutePath());
            operation.get("keystore-password").set(SecurityTestConstants.KEYSTORE_PASSWORD);
            Utils.applyUpdate(operation, managementClient.getControllerClient());

            operation = createOpNode(HTTPS_REALM_SSL_PATH, ModelDescriptionConstants.ADD);
            operation.get(PROTOCOL).set("TLSv1");
            operation.get("keystore-path").set(SERVER_KEYSTORE_FILE.getAbsolutePath());
            operation.get("keystore-password").set(SecurityTestConstants.KEYSTORE_PASSWORD);
            Utils.applyUpdate(operation, managementClient.getControllerClient());

            operation = createOpNode(HTTPS_LISTENER_PATH, ModelDescriptionConstants.ADD);
            operation.get("socket-binding").set(HTTPS);
            operation.get("security-realm").set(HTTPS_REALM);
            Utils.applyUpdate(operation, managementClient.getControllerClient());
            ServerReload.executeReloadAndWaitForCompletion(managementClient.getControllerClient());
        }

        @Override
        protected void nonManagementCleanUp() throws Exception {
            // Delete custom server log file
            Files.delete(logFilePath);

            // Delete folder with HTTPS files
            FileUtils.deleteDirectory(WORK_DIR);
        }
    }

    private static Logger log = Logger.getLogger(RequestDumpingHandlerTestCase.class);

    /** Path to custom server log file. */
    private static Path logFilePath;

    private final String HTTPS_PORT = "8443";

    private static final String DEPLOYMENT = "no-req-dump";
    private static final String DEPLOYMENT_DUMP = "req-dump";
    private static final String DEPLOYMENT_WS = "req-dump-ws";

    /**
	 * Testing app has request dumper handler registered. This test checks that request dump over the HTTPS is generated correctly.
	 */@Test @OperateOnDeployment(DEPLOYMENT_DUMP) public void testReqDumpHandlerOnHttps(@ArquillianResource URL url) throws Exception{URL httpsUrl=new URL("https://" + url.getHost() + ":" + HTTPS_PORT + url.getPath() + "file.txt");new RequestDumpingHandlerTestImpl.HttpsRequestDumpingHandlerTestImpl(httpsUrl.toURI(),logFilePath,true);}

}
