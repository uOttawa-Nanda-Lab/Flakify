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

  @Test public void testGetSuperRangeSlices() throws HectorException{for (int i=0;i < 10;i++){ColumnPath cp=new ColumnPath("Super1");cp.setSuper_column(bytes("SuperColumn_1"));cp.setColumn(bytes("testGetSuperRangeSlices_" + i));keyspace.insert("testGetSuperRangeSlices0",cp,StringSerializer.get().toByteBuffer("testGetSuperRangeSlices_Value_" + i));keyspace.insert("testGetSuperRangeSlices1",cp,StringSerializer.get().toByteBuffer("testGetSuperRangeSlices_Value_" + i));}ColumnParent clp=new ColumnParent("Super1");SliceRange sr=new SliceRange(ByteBuffer.wrap(new byte[0]),ByteBuffer.wrap(new byte[0]),false,150);SlicePredicate sp=new SlicePredicate();sp.setSlice_range(sr);KeyRange range=new KeyRange();range.setStart_key("".getBytes());range.setEnd_key("".getBytes());Map<String, List<SuperColumn>> keySlices=se.fromBytesMap(keyspace.getSuperRangeSlices(clp,sp,range));assertNotNull(keySlices);assertNotNull("testGetSuperRangSlices0 is null",keySlices.get("testGetSuperRangeSlices0"));assertEquals("testGetSuperRangeSlices_Value_0",string(keySlices.get("testGetSuperRangeSlices0").get(0).getColumns().get(0).getValue()));assertEquals(1,keySlices.get("testGetSuperRangeSlices1").size());assertEquals(10,keySlices.get("testGetSuperRangeSlices1").get(0).getColumns().size());ColumnPath cp=new ColumnPath("Super1");keyspace.remove("testGetSuperRangeSlices0",cp);keyspace.remove("testGetSuperRangeSlices1",cp);}



}
