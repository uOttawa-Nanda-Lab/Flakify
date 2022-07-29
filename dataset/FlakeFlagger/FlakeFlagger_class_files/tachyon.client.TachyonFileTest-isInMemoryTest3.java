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

  /**
 * Test LRU Cache Eviction + PIN.
 * @throws InvalidPathException
 * @throws FileAlreadyExistException
 * @throws IOException
 */@Test public void isInMemoryTest3() throws InvalidPathException,FileAlreadyExistException,IOException{int fileId=TestUtils.createByteFile(mTfs,"/pin/file",WriteType.MUST_CACHE,USER_QUOTA_UNIT_BYTES);TachyonFile file=mTfs.getFile(fileId);Assert.assertTrue(file.isInMemory());for (int k=0;k < MAX_FILES;k++){fileId=TestUtils.createByteFile(mTfs,"/file" + k,WriteType.MUST_CACHE,USER_QUOTA_UNIT_BYTES);file=mTfs.getFile(fileId);Assert.assertTrue(file.isInMemory());}CommonUtils.sleepMs(null,WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS);file=mTfs.getFile("/pin/file");Assert.assertTrue(file.isInMemory());file=mTfs.getFile("/file0");Assert.assertFalse(file.isInMemory());for (int k=1;k < MAX_FILES;k++){file=mTfs.getFile("/file" + k);Assert.assertTrue(file.isInMemory());}}
}
