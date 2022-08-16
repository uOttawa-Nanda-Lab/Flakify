package me.prettyprint.cassandra.examples;

import static me.prettyprint.hector.api.factory.HFactory.createKeyspace;
import static me.prettyprint.hector.api.factory.HFactory.getOrCreateCluster;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.testutils.EmbeddedServerHelper;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.exceptions.HectorException;

import org.apache.cassandra.config.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExampleDaoV2Test {

  private static EmbeddedServerHelper embedded;

  /**
   * Set embedded cassandra up and spawn it in a new thread.
   *
   * @throws TTransportException
   * @throws IOException
   * @throws InterruptedException
   */
  @BeforeClass
  public static void setup() throws TTransportException, IOException, InterruptedException, ConfigurationException {
    embedded = new EmbeddedServerHelper();
    embedded.setup();
  }

  @Test
  public void testInsertGetDelete() throws HectorException {
    Cluster c = getOrCreateCluster("MyCluster", "localhost:9170");
    ExampleDaoV2 dao = new ExampleDaoV2(createKeyspace("Keyspace1", c));
    assertNull(dao.get("key", StringSerializer.get()));
    dao.insert("key", "value", StringSerializer.get());
    assertEquals("value", dao.get("key", StringSerializer.get()));
    dao.delete(StringSerializer.get(), "key");
    assertNull(dao.get("key", StringSerializer.get()));
  }
}
