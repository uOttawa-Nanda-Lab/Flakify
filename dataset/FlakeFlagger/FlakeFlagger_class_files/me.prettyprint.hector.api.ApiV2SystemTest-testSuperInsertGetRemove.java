package me.prettyprint.hector.api;

import static me.prettyprint.hector.api.factory.HFactory.createColumn;
import static me.prettyprint.hector.api.factory.HFactory.createColumnQuery;
import static me.prettyprint.hector.api.factory.HFactory.createCountQuery;
import static me.prettyprint.hector.api.factory.HFactory.createKeyspace;
import static me.prettyprint.hector.api.factory.HFactory.createMultigetSliceQuery;
import static me.prettyprint.hector.api.factory.HFactory.createMultigetSubSliceQuery;
import static me.prettyprint.hector.api.factory.HFactory.createMultigetSuperSliceQuery;
import static me.prettyprint.hector.api.factory.HFactory.createMutator;
import static me.prettyprint.hector.api.factory.HFactory.createRangeSlicesQuery;
import static me.prettyprint.hector.api.factory.HFactory.createRangeSubSlicesQuery;
import static me.prettyprint.hector.api.factory.HFactory.createRangeSuperSlicesQuery;
import static me.prettyprint.hector.api.factory.HFactory.createSliceQuery;
import static me.prettyprint.hector.api.factory.HFactory.createSubColumnQuery;
import static me.prettyprint.hector.api.factory.HFactory.createSubCountQuery;
import static me.prettyprint.hector.api.factory.HFactory.createSubSliceQuery;
import static me.prettyprint.hector.api.factory.HFactory.createSuperColumn;
import static me.prettyprint.hector.api.factory.HFactory.createSuperColumnQuery;
import static me.prettyprint.hector.api.factory.HFactory.createSuperCountQuery;
import static me.prettyprint.hector.api.factory.HFactory.createSuperSliceQuery;
import static me.prettyprint.hector.api.factory.HFactory.getOrCreateCluster;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import me.prettyprint.cassandra.BaseEmbededServerSetupTest;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.HSuperColumn;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.OrderedSuperRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.beans.Rows;
import me.prettyprint.hector.api.beans.SuperRow;
import me.prettyprint.hector.api.beans.SuperRows;
import me.prettyprint.hector.api.beans.SuperSlice;
import me.prettyprint.hector.api.mutation.MutationResult;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.CountQuery;
import me.prettyprint.hector.api.query.MultigetSliceQuery;
import me.prettyprint.hector.api.query.MultigetSubSliceQuery;
import me.prettyprint.hector.api.query.MultigetSuperSliceQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;
import me.prettyprint.hector.api.query.RangeSubSlicesQuery;
import me.prettyprint.hector.api.query.RangeSuperSlicesQuery;
import me.prettyprint.hector.api.query.SliceQuery;
import me.prettyprint.hector.api.query.SubColumnQuery;
import me.prettyprint.hector.api.query.SubCountQuery;
import me.prettyprint.hector.api.query.SubSliceQuery;
import me.prettyprint.hector.api.query.SuperColumnQuery;
import me.prettyprint.hector.api.query.SuperCountQuery;
import me.prettyprint.hector.api.query.SuperSliceQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiV2SystemTest extends BaseEmbededServerSetupTest {

  private static final Logger log = LoggerFactory.getLogger(ApiV2SystemTest.class);
  private final static String KEYSPACE = "Keyspace1";
  private static final StringSerializer se = new StringSerializer();
  private Cluster cluster;
  private Keyspace ko;

  @Before
  public void setupCase() {
    cluster = getOrCreateCluster("MyCluster", "127.0.0.1:9170");
    ko = createKeyspace(KEYSPACE, cluster);
  }

  @Test public void testSuperInsertGetRemove(){String cf="Super1";Mutator<String> m=createMutator(ko,se);@SuppressWarnings("unchecked") List<HColumn<String, String>> columns=Arrays.asList(createColumn("name1","value1",se,se),createColumn("name2","value2",se,se));m.insert("testSuperInsertGetRemove",cf,createSuperColumn("testSuperInsertGetRemove",columns,se,se,se));SuperColumnQuery<String, String, String, String> q=createSuperColumnQuery(ko,se,se,se,se);q.setSuperName("testSuperInsertGetRemove").setColumnFamily(cf);QueryResult<HSuperColumn<String, String, String>> r=q.setKey("testSuperInsertGetRemove").execute();assertNotNull(r);HSuperColumn<String, String, String> sc=r.get();assertNotNull(sc);assertEquals(2,sc.getSize());HColumn<String, String> c=sc.get(0);String value=c.getValue();assertEquals("value1",value);String name=c.getName();assertEquals("name1",name);HColumn<String, String> c2=sc.get(1);assertEquals("name2",c2.getName());assertEquals("value2",c2.getValue());m=createMutator(ko,se);m.subDelete("testSuperInsertGetRemove",cf,"testSuperInsertGetRemove",null,se,se);r=q.execute();sc=r.get();assertNull(sc);}

  private void deleteColumns(TestCleanupDescriptor cleanup) {
    Mutator<String> m = createMutator(ko, se);
    for (int i = 0; i < cleanup.rowCount; ++i) {
      for (int j = 0; j < cleanup.columnCount; ++j) {
        m.addDeletion(cleanup.rowPrefix + i, cleanup.cf, cleanup.columnsPrefix + j, se);
      }
    }
    m.execute();
  }

  private TestCleanupDescriptor insertSuperColumns(String cf, int rowCount, String rowPrefix,
      int scCount, String scPrefix) {
    Mutator<String> m = createMutator(ko, se);
    for (int i = 0; i < rowCount; ++i) {
      for (int j = 0; j < scCount; ++j) {
        @SuppressWarnings("unchecked")
        HSuperColumn<String, String, String> sc = createSuperColumn(
            scPrefix + j,
            Arrays.asList(createColumn("c0" + i + j, "v0" + i + j, se, se),
                createColumn("c1" + 1 + j, "v1" + i + j, se, se)), se, se, se);
        m.addInsertion(rowPrefix + i, cf, sc);
      }
    }
    m.execute();
    return new TestCleanupDescriptor(cf, rowCount, rowPrefix, scCount, scPrefix);
  }

  private TestCleanupDescriptor insertColumns(String cf, int rowCount, String rowPrefix,
      int columnCount, String columnPrefix) {
    Mutator<String> m = createMutator(ko, se);
    for (int i = 0; i < rowCount; ++i) {
      for (int j = 0; j < columnCount; ++j) {
        m.addInsertion(rowPrefix + i, cf, createColumn(columnPrefix + j, "value" + i + j, se, se));
      }
    }
    MutationResult mr = m.execute();
    assertTrue("Time should be > 0", mr.getExecutionTimeMicro() > 0);
    log.debug("insert execution time: {}", mr.getExecutionTimeMicro());
    log.debug(mr.toString());
    return new TestCleanupDescriptor(cf, rowCount, rowPrefix, columnCount, columnPrefix);
  }

  /**
   * A class describing what kind of cleanup is required at the end of the test. Just some
   * bookeeping, that's all.
   *
   * @author Ran Tavory
   *
   */
  private static class TestCleanupDescriptor {
    public final String cf;
    public final int rowCount;
    public final String rowPrefix;
    public final int columnCount;
    public final String columnsPrefix;

    public TestCleanupDescriptor(String cf, int rowCount, String rowPrefix, int scCount,
        String scPrefix) {
      this.cf = cf;
      this.rowCount = rowCount;
      this.rowPrefix = rowPrefix;
      this.columnCount = scCount;
      this.columnsPrefix = scPrefix;
    }
  }
}
