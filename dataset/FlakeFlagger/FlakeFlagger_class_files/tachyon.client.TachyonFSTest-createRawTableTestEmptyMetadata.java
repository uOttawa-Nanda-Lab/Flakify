package tachyon.client;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tachyon.CommonUtils;
import tachyon.Constants;
import tachyon.LocalTachyonCluster;
import tachyon.TestUtils;
import tachyon.UnderFileSystem;
import tachyon.client.table.RawTable;
import tachyon.thrift.ClientWorkerInfo;

/**
 * Unit tests on TachyonClient.
 */
public class TachyonFSTest {
  private final int WORKER_CAPACITY_BYTES = 20000;
  private final int USER_QUOTA_UNIT_BYTES = 1000;
  private final int WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS = 3;
  private final int SLEEP_MS = WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS * 2 + 2;
  private LocalTachyonCluster mLocalTachyonCluster = null;
  private TachyonFS mTfs = null;

  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", USER_QUOTA_UNIT_BYTES + "");
    System.setProperty("tachyon.worker.to.master.heartbeat.interval.ms",
        WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS + "");
    mLocalTachyonCluster = new LocalTachyonCluster(WORKER_CAPACITY_BYTES);
    mLocalTachyonCluster.start();
    mTfs = mLocalTachyonCluster.getClient();
  }

  @Test public void createRawTableTestEmptyMetadata() throws IOException{int fileId=mTfs.createRawTable("/tables/table1",20);RawTable table=mTfs.getRawTable(fileId);Assert.assertEquals(fileId,table.getId());Assert.assertEquals("/tables/table1",table.getPath());Assert.assertEquals(20,table.getColumns());Assert.assertEquals(ByteBuffer.allocate(0),table.getMetadata());table=mTfs.getRawTable("/tables/table1");Assert.assertEquals(fileId,table.getId());Assert.assertEquals("/tables/table1",table.getPath());Assert.assertEquals(20,table.getColumns());Assert.assertEquals(ByteBuffer.allocate(0),table.getMetadata());}
}
