package io.undertow.server.handlers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.testutils.DefaultServer;
import io.undertow.testutils.HttpClientUtils;
import io.undertow.testutils.TestHttpClient;
import io.undertow.util.CompletionLatchHandler;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Tests writing the database (in memory)
 *
 * @author Filipe Ferraz
 */

@RunWith(DefaultServer.class)
public class JDBCLogDatabaseTestCase {

    private static final int NUM_THREADS = 10;
    private static final int NUM_REQUESTS = 12;

    private static final HttpHandler HELLO_HANDLER = new HttpHandler() {
        @Override
        public void handleRequest(final HttpServerExchange exchange) throws Exception {
            exchange.getResponseSender().send("Hello");
        }
    };

    private JdbcConnectionPool ds;


    @Before
    public void setup() throws SQLException {
        ds = JdbcConnectionPool.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user", "password");
        Connection conn = null;
        Statement statement = null;
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(true);
            statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE PUBLIC.ACCESS (" +
                    " id SERIAL NOT NULL," +
                    " remoteHost CHAR(15) NOT NULL," +
                    " userName CHAR(15)," +
                    " timestamp TIMESTAMP NOT NULL," +
                    " virtualHost VARCHAR(64)," +
                    " method VARCHAR(8)," +
                    " query VARCHAR(255) NOT NULL," +
                    " status SMALLINT UNSIGNED NOT NULL," +
                    " bytes INT UNSIGNED NOT NULL," +
                    " referer VARCHAR(128)," +
                    " userAgent VARCHAR(128)," +
                    " PRIMARY KEY (id)" +
                    " );");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }

        }

    }

    @Test public void testSingleLogMessageToDatabase() throws IOException,InterruptedException,SQLException{JDBCLogHandler logHandler=new JDBCLogHandler(HELLO_HANDLER,DefaultServer.getWorker(),"common",ds);CompletionLatchHandler latchHandler;DefaultServer.setRootHandler(latchHandler=new CompletionLatchHandler(logHandler));TestHttpClient client=new TestHttpClient();try {HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/path");HttpResponse result=client.execute(get);latchHandler.await();logHandler.awaitWrittenForTest();Assert.assertEquals(200,result.getStatusLine().getStatusCode());Assert.assertEquals("Hello",HttpClientUtils.readResponse(result));}  finally {Connection conn=null;Statement statement=null;try {conn=ds.getConnection();statement=conn.createStatement();ResultSet resultDatabase=statement.executeQuery("SELECT * FROM PUBLIC.ACCESS;");resultDatabase.next();Assert.assertEquals("127.0.0.1",resultDatabase.getString(logHandler.getRemoteHostField()));Assert.assertEquals("5",resultDatabase.getString(logHandler.getBytesField()));Assert.assertEquals("200",resultDatabase.getString(logHandler.getStatusField()));client.getConnectionManager().shutdown();}  finally {if (statement != null){statement.close();}if (conn != null){conn.close();}}}}

}
