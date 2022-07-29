package tachyon.client;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tachyon.CommonUtils;
import tachyon.LocalTachyonCluster;
import tachyon.TestUtils;
import tachyon.thrift.FileAlreadyExistException;
import tachyon.thrift.InvalidPathException;

/**
 * Unit tests for tachyon.client.TachyonFile.
 */
public class TachyonFileTest {
  private LocalTachyonCluster mLocalTachyonCluster = null;
  private TachyonFS mTfs = null;
  private final int WORKER_CAPACITY_BYTES = 1000;
  private final int USER_QUOTA_UNIT_BYTES = 100;
  private final int WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS = 5;
  private final String PIN_DATA = "/pin";
  private final int MAX_FILES = WORKER_CAPACITY_BYTES / USER_QUOTA_UNIT_BYTES;

  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", USER_QUOTA_UNIT_BYTES + "");
    System.setProperty("tachyon.worker.to.master.heartbeat.interval.ms",
        WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS + "");
    System.setProperty("tachyon.master.pinlist", PIN_DATA);
    mLocalTachyonCluster = new LocalTachyonCluster(WORKER_CAPACITY_BYTES);
    mLocalTachyonCluster.start();
    mTfs = mLocalTachyonCluster.getClient();
  }

  @Test public void writeEmptyFileTest() throws IOException{Assert.assertEquals(2,mTfs.createFile("/emptyFile"));Assert.assertTrue(mTfs.exist("/emptyFile"));TachyonFile file=mTfs.getFile("/emptyFile");Assert.assertEquals(0,file.length());OutStream os=file.getOutStream(WriteType.CACHE_THROUGH);os.close();Assert.assertEquals(0,file.length());}
}
