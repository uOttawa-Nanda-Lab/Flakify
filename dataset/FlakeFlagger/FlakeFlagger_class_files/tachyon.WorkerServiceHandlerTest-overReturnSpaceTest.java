package tachyon;

import tachyon.client.TachyonFS;
import tachyon.client.WriteType;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import org.apache.thrift.TException;
import tachyon.thrift.FileAlreadyExistException;
import tachyon.thrift.FileDoesNotExistException;
import tachyon.thrift.InvalidPathException;
import tachyon.thrift.ClientFileInfo;

/**
 * Unit tests for tachyon.WorkerServiceHandler
 */
public class WorkerServiceHandlerTest {
  private LocalTachyonCluster mLocalTachyonCluster = null;
  private MasterInfo mMasterInfo = null;
  private WorkerServiceHandler mWorkerServiceHandler = null;
  private TachyonFS mTfs = null;
  private final long WORKER_CAPACITY_BYTES = 10000;
  private final int WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS = 5;

  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS + "");
    System.setProperty("tachyon.worker.to.master.heartbeat.interval.ms",
        WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS + "");
    mLocalTachyonCluster = new LocalTachyonCluster(WORKER_CAPACITY_BYTES);
    mLocalTachyonCluster.start();
    mWorkerServiceHandler = mLocalTachyonCluster.getWorkerServiceHandler();
    mMasterInfo = mLocalTachyonCluster.getMasterInfo();
    mTfs = mLocalTachyonCluster.getClient();
  }

  @Test public void overReturnSpaceTest() throws TException{Assert.assertTrue(mWorkerServiceHandler.requestSpace(1,WORKER_CAPACITY_BYTES / 10));Assert.assertTrue(mWorkerServiceHandler.requestSpace(2,WORKER_CAPACITY_BYTES / 10));mWorkerServiceHandler.returnSpace(1,WORKER_CAPACITY_BYTES);Assert.assertFalse(mWorkerServiceHandler.requestSpace(1,WORKER_CAPACITY_BYTES));}
}