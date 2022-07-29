package me.prettyprint.cassandra.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import me.prettyprint.cassandra.BaseEmbededServerSetupTest;
import me.prettyprint.cassandra.model.BasicColumnDefinition;
import me.prettyprint.cassandra.model.BasicColumnFamilyDefinition;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.ddl.ColumnFamilyDefinition;
import me.prettyprint.hector.api.ddl.ColumnIndexType;
import me.prettyprint.hector.api.ddl.ComparatorType;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.factory.HFactory;

import org.apache.cassandra.thrift.NotFoundException;
import org.apache.cassandra.thrift.TokenRange;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class CassandraClusterTest extends BaseEmbededServerSetupTest {

  private ThriftCluster cassandraCluster;
  private CassandraHostConfigurator cassandraHostConfigurator;


  @Before
  public void setupCase() throws TTransportException, TException, IllegalArgumentException,
          NotFoundException, UnknownHostException, Exception {
    cassandraHostConfigurator = new CassandraHostConfigurator("localhost:9170");
    cassandraCluster = new ThriftCluster("Test Cluster", cassandraHostConfigurator);
  }

  @Test public void testEditColumnFamily() throws Exception{BasicColumnFamilyDefinition columnFamilyDefinition=new BasicColumnFamilyDefinition();columnFamilyDefinition.setKeyspaceName("DynKeyspace3");columnFamilyDefinition.setName("DynamicCF");ColumnFamilyDefinition cfDef=new ThriftCfDef(columnFamilyDefinition);KeyspaceDefinition keyspaceDefinition=HFactory.createKeyspaceDefinition("DynKeyspace3","org.apache.cassandra.locator.SimpleStrategy",1,Arrays.asList(cfDef));cassandraCluster.addKeyspace(keyspaceDefinition);KeyspaceDefinition fromCluster=cassandraCluster.describeKeyspace("DynKeyspace3");cfDef=fromCluster.getCfDefs().get(0);columnFamilyDefinition=new BasicColumnFamilyDefinition(cfDef);BasicColumnDefinition columnDefinition=new BasicColumnDefinition();columnDefinition.setName(StringSerializer.get().toByteBuffer("birthdate"));columnDefinition.setIndexType(ColumnIndexType.KEYS);columnDefinition.setValidationClass(ComparatorType.LONGTYPE.getClassName());columnFamilyDefinition.addColumnDefinition(columnDefinition);cassandraCluster.updateColumnFamily(new ThriftCfDef(columnFamilyDefinition));fromCluster=cassandraCluster.describeKeyspace("DynKeyspace3");assertEquals("birthdate",StringSerializer.get().fromByteBuffer(fromCluster.getCfDefs().get(0).getColumnMetadata().get(0).getName()));}
}
