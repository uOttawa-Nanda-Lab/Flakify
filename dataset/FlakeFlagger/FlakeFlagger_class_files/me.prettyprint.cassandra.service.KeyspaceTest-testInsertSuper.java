package me.prettyprint.cassandra.service;

import static me.prettyprint.cassandra.utils.StringUtils.bytes;
import static me.prettyprint.cassandra.utils.StringUtils.string;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import me.prettyprint.cassandra.BaseEmbededServerSetupTest;
import me.prettyprint.cassandra.model.QuorumAllConsistencyLevelPolicy;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.HConsistencyLevel;
import me.prettyprint.hector.api.exceptions.HNotFoundException;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.exceptions.PoolExhaustedException;

import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ColumnPath;
//import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.Deletion;
import org.apache.cassandra.thrift.KeyRange;
import org.apache.cassandra.thrift.Mutation;
import org.apache.cassandra.thrift.SlicePredicate;
import org.apache.cassandra.thrift.SliceRange;
import org.apache.cassandra.thrift.SuperColumn;
import org.junit.Before;
import org.junit.Test;

/**
 * For the tests we assume the following structure:
 *
 * &lt;Keyspaces&gt; &lt;Keyspace Name="Keyspace1"&gt; &lt;ColumnFamily
 * CompareWith="BytesType" Name="Standard1" FlushPeriodInMinutes="60"/&gt;
 * &lt;ColumnFamily CompareWith="UTF8Type" Name="Standard2"/&gt;
 * &lt;ColumnFamily CompareWith="TimeUUIDType" Name="StandardByUUID1"/&gt;
 * &lt;ColumnFamily ColumnType="Super" CompareWith="UTF8Type"
 * CompareSubcolumnsWith="UTF8Type" Name="Super1"/&gt;
 *
 * @author Ran Tavory (rantav@gmail.com)
 * @author zznate (nate@riptano.com)
 */
public class KeyspaceTest extends BaseEmbededServerSetupTest {

  private KeyspaceService keyspace;
  private static final StringSerializer se = new StringSerializer();

  @Before
  public void setupCase() throws IllegalStateException, PoolExhaustedException, Exception {
    super.setupClient();
    
    keyspace = new KeyspaceServiceImpl("Keyspace1", new QuorumAllConsistencyLevelPolicy(), 
        connectionManager, FailoverPolicy.ON_FAIL_TRY_ALL_AVAILABLE);
  }

  /**
   * Test insertion of a supercolumn using insert
   */
  @Test
  public void testInsertSuper() throws IllegalArgumentException, NoSuchElementException,
  IllegalStateException, HNotFoundException, Exception {

    // insert value
    ColumnParent columnParent = new ColumnParent("Super1");
    columnParent.setSuper_column(StringSerializer.get().toByteBuffer("testInsertSuper_super"));
    Column column = new Column(StringSerializer.get().toByteBuffer("testInsertSuper_column"), 
        StringSerializer.get().toByteBuffer("testInsertSuper_value"), connectionManager.createClock());



    keyspace.insert(StringSerializer.get().toByteBuffer("testInsertSuper_key"), columnParent, column);
    column.setName(StringSerializer.get().toByteBuffer("testInsertSuper_column2"));
    keyspace.insert(StringSerializer.get().toByteBuffer("testInsertSuper_key"), columnParent, column);

    // get value and assert
    ColumnPath cp2 = new ColumnPath("Super1");
    cp2.setSuper_column(bytes("testInsertSuper_super"));
    SuperColumn sc = keyspace.getSuperColumn("testInsertSuper_key", cp2);
    assertNotNull(sc);
    assertEquals("testInsertSuper_super", string(sc.getName()));
    assertEquals(2, sc.getColumns().size());
    assertEquals("testInsertSuper_value", string(sc.getColumns().get(0).getValue()));

    // remove value
    keyspace.remove("testInsertSuper_super", cp2);
  }



}
