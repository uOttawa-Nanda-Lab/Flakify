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

  @Test public void lockBlockTest3() throws IOException{TachyonFile tFile=null;int numOfFiles=5;int fileSize=WORKER_CAPACITY_BYTES / numOfFiles;List<Integer> fileIds=new ArrayList<Integer>();for (int k=0;k < numOfFiles;k++){fileIds.add(TestUtils.createByteFile(mTfs,"/file_" + k,WriteType.CACHE_THROUGH,fileSize));}for (int k=0;k < numOfFiles;k++){tFile=mTfs.getFile(fileIds.get(k));Assert.assertTrue(tFile.isInMemory());if (k < numOfFiles - 1){Assert.assertNotNull(tFile.readByteBuffer());}}fileIds.add(TestUtils.createByteFile(mTfs,"/file_" + numOfFiles,WriteType.CACHE_THROUGH,fileSize));for (int k=0;k <= numOfFiles;k++){tFile=mTfs.getFile(fileIds.get(k));if (k != numOfFiles - 1){Assert.assertTrue(tFile.isInMemory());} else {CommonUtils.sleepMs(null,SLEEP_MS);Assert.assertFalse(tFile.isInMemory());}}}
}
