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

  @Test public void testBatchUpdateInsertAndDelOnSame() throws HectorException{ColumnPath sta1=new ColumnPath("Standard1");sta1.setColumn(bytes("deleteThroughInserBatch_col"));keyspace.insert("deleteThroughInserBatch_key",sta1,StringSerializer.get().toByteBuffer("deleteThroughInserBatch_val"));Column found=keyspace.getColumn("deleteThroughInserBatch_key",sta1);assertNotNull(found);BatchMutation<String> batchMutation=new BatchMutation<String>(StringSerializer.get());List<String> columnFamilies=Arrays.asList("Standard1");for (int i=0;i < 10;i++){for (int j=0;j < 10;j++){Column col=new Column(StringSerializer.get().toByteBuffer("testBatchMutateColumn_" + j),StringSerializer.get().toByteBuffer("testBatchMutateColumn_value_" + j),connectionManager.createClock());batchMutation.addInsertion("testBatchMutateColumn_" + i,columnFamilies,col);}}SlicePredicate slicePredicate=new SlicePredicate();slicePredicate.addToColumn_names(StringSerializer.get().toByteBuffer("deleteThroughInserBatch_col"));Deletion deletion=new Deletion(connectionManager.createClock());deletion.setPredicate(slicePredicate);batchMutation.addDeletion("deleteThroughInserBatch_key",columnFamilies,deletion);keyspace.batchMutate(batchMutation);try {keyspace.getColumn("deleteThroughInserBatch_key",sta1);fail("Should not have found a value here");} catch (Exception e){}for (int i=0;i < 10;i++){for (int j=0;j < 10;j++){ColumnPath cp=new ColumnPath("Standard1");cp.setColumn(bytes("testBatchMutateColumn_" + j));Column col=keyspace.getColumn("testBatchMutateColumn_" + i,cp);assertNotNull(col);String value=string(col.getValue());assertEquals("testBatchMutateColumn_value_" + j,value);}}}



}
