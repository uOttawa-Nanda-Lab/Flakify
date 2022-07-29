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
 * Basic isInMemory Test.
 * @throws InvalidPathException
 * @throws FileAlreadyExistException
 * @throws IOException
 */@Test public void isInMemoryTest() throws InvalidPathException,FileAlreadyExistException,IOException{int fileId=TestUtils.createByteFile(mTfs,"/file1",WriteType.MUST_CACHE,USER_QUOTA_UNIT_BYTES);TachyonFile file=mTfs.getFile(fileId);Assert.assertTrue(file.isInMemory());fileId=TestUtils.createByteFile(mTfs,"/file2",WriteType.CACHE_THROUGH,USER_QUOTA_UNIT_BYTES);file=mTfs.getFile(fileId);Assert.assertTrue(file.isInMemory());fileId=TestUtils.createByteFile(mTfs,"/file3",WriteType.THROUGH,USER_QUOTA_UNIT_BYTES);file=mTfs.getFile(fileId);Assert.assertFalse(file.isInMemory());Assert.assertTrue(file.recache());Assert.assertTrue(file.isInMemory());fileId=TestUtils.createByteFile(mTfs,"/file4",WriteType.THROUGH,WORKER_CAPACITY_BYTES + 1);file=mTfs.getFile(fileId);Assert.assertFalse(file.isInMemory());Assert.assertFalse(file.recache());Assert.assertFalse(file.isInMemory());fileId=TestUtils.createByteFile(mTfs,"/file5",WriteType.THROUGH,WORKER_CAPACITY_BYTES);file=mTfs.getFile(fileId);Assert.assertFalse(file.isInMemory());Assert.assertTrue(file.recache());Assert.assertTrue(file.isInMemory());}
}
