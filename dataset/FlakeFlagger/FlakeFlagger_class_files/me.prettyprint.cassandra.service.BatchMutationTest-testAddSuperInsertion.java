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

  @Test public void testAddSuperInsertion(){SuperColumn sc=new SuperColumn(StringSerializer.get().toByteBuffer("c_name"),Arrays.asList(new Column(StringSerializer.get().toByteBuffer("c_name"),StringSerializer.get().toByteBuffer("c_val"),System.currentTimeMillis())));batchMutate.addSuperInsertion("key1",columnFamilies,sc);assertEquals(1,batchMutate.getMutationMap().get(StringSerializer.get().toByteBuffer("key1")).size());SuperColumn sc2=new SuperColumn(StringSerializer.get().toByteBuffer("c_name2"),Arrays.asList(new Column(StringSerializer.get().toByteBuffer("c_name"),StringSerializer.get().toByteBuffer("c_val"),System.currentTimeMillis())));batchMutate.addSuperInsertion("key1",columnFamilies,sc2);assertEquals(2,batchMutate.getMutationMap().get(StringSerializer.get().toByteBuffer("key1")).get("Standard1").size());}
}
