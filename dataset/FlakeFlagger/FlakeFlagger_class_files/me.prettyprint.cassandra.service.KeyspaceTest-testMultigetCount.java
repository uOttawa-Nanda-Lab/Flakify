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

  @Test
  public void testMultigetCount() {
    // insert 25 columns into 10 rows
    List<ByteBuffer> keys = new ArrayList<ByteBuffer>();
    for ( int j=0; j < 10; j++ ) {
      for (int i = 0; i < 25; i++) {
        ColumnPath cp = new ColumnPath("Standard1");
        cp.setColumn(StringSerializer.get().toByteBuffer("testMultigetCount_column_" + i));
        keyspace.insert("testMultigetCount_key_"+j, cp, StringSerializer.get().toByteBuffer("testMultigetCount_value_" + i));
      }
      if (j % 2 == 0) {
        keys.add(StringSerializer.get().toByteBuffer("testMultigetCount_key_"+j));
      }
    }

    // get value
    ColumnParent clp = new ColumnParent("Standard1");
    SlicePredicate slicePredicate = new SlicePredicate();
    SliceRange sr = new SliceRange(ByteBuffer.wrap(new byte[0]), ByteBuffer.wrap(new byte[0]), false, 150);
    slicePredicate.setSlice_range(sr);
    Map<ByteBuffer,Integer> counts = keyspace.multigetCount(keys, clp, slicePredicate);
    assertEquals(5,counts.size());
    assertEquals(new Integer(25),counts.entrySet().iterator().next().getValue());

    slicePredicate.setSlice_range(new SliceRange(StringSerializer.get().toByteBuffer(""), 
        StringSerializer.get().toByteBuffer(""), false, 5));
    counts = keyspace.multigetCount(keys, clp, slicePredicate);

    assertEquals(5,counts.size());
    assertEquals(new Integer(5),counts.entrySet().iterator().next().getValue());

  }



}
