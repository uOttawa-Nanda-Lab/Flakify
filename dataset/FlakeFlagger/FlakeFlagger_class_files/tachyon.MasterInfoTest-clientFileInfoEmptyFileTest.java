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

  @Test public void clientFileInfoEmptyFileTest() throws InvalidPathException,FileDoesNotExistException,FileAlreadyExistException,BlockInfoException,TachyonException{int fileId=mMasterInfo.createFile("/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);ClientFileInfo fileInfo=mMasterInfo.getClientFileInfo("/testFile");Assert.assertEquals("testFile",fileInfo.getName());Assert.assertEquals(fileId,fileInfo.getId());Assert.assertEquals(0,fileInfo.getLength());Assert.assertEquals("",fileInfo.getCheckpointPath());Assert.assertFalse(fileInfo.isFolder());Assert.assertFalse(fileInfo.isNeedPin());Assert.assertTrue(fileInfo.isNeedCache());Assert.assertFalse(fileInfo.isComplete());}
}