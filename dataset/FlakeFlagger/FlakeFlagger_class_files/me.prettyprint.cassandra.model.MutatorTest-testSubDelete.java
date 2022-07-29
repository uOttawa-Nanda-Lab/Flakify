package me.prettyprint.cassandra.model;

import static me.prettyprint.hector.api.factory.HFactory.createColumn;
import static me.prettyprint.hector.api.factory.HFactory.createColumnQuery;
import static me.prettyprint.hector.api.factory.HFactory.createKeyspace;
import static me.prettyprint.hector.api.factory.HFactory.createMutator;
import static me.prettyprint.hector.api.factory.HFactory.createSuperColumn;
import static me.prettyprint.hector.api.factory.HFactory.getOrCreateCluster;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.BaseEmbededServerSetupTest;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.utils.StringUtils;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.HSuperColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.MutationResult;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SuperColumnQuery;

import org.apache.cassandra.thrift.ColumnPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class MutatorTest extends BaseEmbededServerSetupTest {

  private static final StringSerializer se = new StringSerializer();

  private Cluster cluster;
  private Keyspace keyspace;

  @Before
  public void setupCase() {
    cluster = getOrCreateCluster("Test Cluster", "127.0.0.1:9170");
    keyspace = createKeyspace("Keyspace1", cluster);
  }

  @Test public void testSubDelete(){Mutator<String> m=createMutator(keyspace,se);List<HColumn<String, String>> columnList=new ArrayList<HColumn<String, String>>();columnList.add(createColumn("col_1","val_1",se,se));columnList.add(createColumn("col_2","val_2",se,se));columnList.add(createColumn("col_3","val_3",se,se));HSuperColumn<String, String, String> superColumn=createSuperColumn("super_name",columnList,se,se,se);MutationResult r=m.insert("sk1","Super1",superColumn);SuperColumnQuery<String, String, String, String> scq=HFactory.createSuperColumnQuery(keyspace,se,se,se,se);scq.setColumnFamily("Super1");scq.setKey("sk1");scq.setSuperName("super_name");assertEquals(3,scq.execute().get().getColumns().size());m.discardPendingMutations();columnList.remove(1);columnList.remove(0);superColumn.setSubcolumns(columnList);m.addSubDelete("sk1","Super1",superColumn);m.execute();assertEquals(2,scq.execute().get().getColumns().size());}
  
  private void assertColumnExists(String keyspace, String cf, String key, String column) {
    ColumnPath cp = new ColumnPath(cf);
    cp.setColumn(StringUtils.bytes(column));
    Keyspace ks = HFactory.createKeyspace(keyspace, cluster);
    ColumnQuery<String, String, String> columnQuery = HFactory.createStringColumnQuery(ks);
    assertNotNull(String.format("Should have value for %s.%s[%s][%s]", keyspace, cf, key, column),
        columnQuery.setColumnFamily(cf).setKey(key).setName(column).execute().get().getValue());
        //client.getKeyspace(keyspace).getColumn(key, cp));
    //cluster.releaseClient(client);
  }

}
