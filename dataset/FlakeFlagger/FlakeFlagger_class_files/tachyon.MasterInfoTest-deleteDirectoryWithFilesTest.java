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

  @Test public void deleteDirectoryWithFilesTest() throws InvalidPathException,FileAlreadyExistException,TachyonException,BlockInfoException{Assert.assertTrue(mMasterInfo.mkdir("/testFolder"));int fileId=mMasterInfo.createFile("/testFolder/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);Assert.assertEquals(2,mMasterInfo.getFileId("/testFolder"));Assert.assertEquals(fileId,mMasterInfo.getFileId("/testFolder/testFile"));Assert.assertTrue(mMasterInfo.delete(2,true));Assert.assertEquals(-1,mMasterInfo.getFileId("/testFolder"));Assert.assertEquals(-1,mMasterInfo.getFileId("/testFolder/testFile"));}
}