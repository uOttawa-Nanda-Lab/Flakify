package tachyon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import tachyon.thrift.BlockInfoException;
import tachyon.thrift.ClientFileInfo;
import tachyon.thrift.InvalidPathException;
import tachyon.thrift.FileAlreadyExistException;
import tachyon.thrift.FileDoesNotExistException;
import tachyon.thrift.TableColumnException;
import tachyon.thrift.SuspectedFileSizeException;
import tachyon.thrift.TachyonException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for tachyon.MasterInfo
 */
public class MasterInfoTest {
  private LocalTachyonCluster mLocalTachyonCluster = null;
  private MasterInfo mMasterInfo = null;

  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", "1000");
    mLocalTachyonCluster = new LocalTachyonCluster(1000);
    mLocalTachyonCluster.start();
    mMasterInfo = mLocalTachyonCluster.getMasterInfo();
  }

  @Test public void clientFileInfoDirectoryTest() throws InvalidPathException,FileDoesNotExistException,FileAlreadyExistException,TachyonException{Assert.assertTrue(mMasterInfo.mkdir("/testFolder"));ClientFileInfo fileInfo=mMasterInfo.getClientFileInfo("/testFolder");Assert.assertEquals("testFolder",fileInfo.getName());Assert.assertEquals(2,fileInfo.getId());Assert.assertEquals(0,fileInfo.getLength());Assert.assertEquals("",fileInfo.getCheckpointPath());Assert.assertTrue(fileInfo.isFolder());Assert.assertFalse(fileInfo.isNeedPin());Assert.assertFalse(fileInfo.isNeedCache());Assert.assertTrue(fileInfo.isComplete());}
}