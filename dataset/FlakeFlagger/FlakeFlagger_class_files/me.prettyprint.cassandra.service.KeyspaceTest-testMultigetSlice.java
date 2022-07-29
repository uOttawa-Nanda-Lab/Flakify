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

  @Test public void testMultigetSlice() throws HectorException{ColumnPath cp=new ColumnPath("Standard1");cp.setColumn(bytes("testMultigetSlice"));ArrayList<String> keys=new ArrayList<String>(100);for (int i=0;i < 100;i++){keyspace.insert("testMultigetSlice_" + i,cp,StringSerializer.get().toByteBuffer("testMultigetSlice_value_" + i));keys.add("testMultigetSlice_" + i);}ColumnParent clp=new ColumnParent("Standard1");SliceRange sr=new SliceRange(ByteBuffer.wrap(new byte[0]),ByteBuffer.wrap(new byte[0]),false,150);SlicePredicate sp=new SlicePredicate();sp.setSlice_range(sr);Map<String, List<Column>> ms=se.fromBytesMap(keyspace.multigetSlice(se.toBytesList(keys),clp,sp));for (int i=0;i < 100;i++){List<Column> cl=ms.get(keys.get(i));assertNotNull(cl);assertEquals(1,cl.size());assertTrue(string(cl.get(0).getValue()).startsWith("testMultigetSlice_"));}for (int i=0;i < 100;i++){keyspace.remove("testMultigetSlice_" + i,cp);}}



}
