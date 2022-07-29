package me.prettyprint.cassandra.service;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import me.prettyprint.cassandra.serializers.StringSerializer;

import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.Deletion;
import org.apache.cassandra.thrift.Mutation;
import org.apache.cassandra.thrift.SlicePredicate;
import org.apache.cassandra.thrift.SuperColumn;
import org.junit.Before;
import org.junit.Test;

public class BatchMutationTest {

  private List<String> columnFamilies;
  private BatchMutation<String> batchMutate;

  @Before
  public void setup() {
    columnFamilies = new ArrayList<String>();
    columnFamilies.add("Standard1");
    batchMutate = new BatchMutation<String>(StringSerializer.get());
  }

  @Test public void testAddInsertion(){Column column=new Column(StringSerializer.get().toByteBuffer("c_name"),StringSerializer.get().toByteBuffer("c_val"),System.currentTimeMillis());batchMutate.addInsertion("key1",columnFamilies,column);Map<ByteBuffer, Map<String, List<Mutation>>> mutationMap=batchMutate.getMutationMap();assertEquals(1,mutationMap.get(StringSerializer.get().toByteBuffer("key1")).size());Column column2=new Column(StringSerializer.get().toByteBuffer("c_name2"),StringSerializer.get().toByteBuffer("c_val2"),System.currentTimeMillis());batchMutate.addInsertion("key1",columnFamilies,column2);assertEquals(2,mutationMap.get(StringSerializer.get().toByteBuffer("key1")).get("Standard1").size());}
}
