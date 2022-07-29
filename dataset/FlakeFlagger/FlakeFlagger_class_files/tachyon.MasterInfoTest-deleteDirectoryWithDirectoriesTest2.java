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

  @Test public void deleteDirectoryWithDirectoriesTest2() throws InvalidPathException,FileAlreadyExistException,TachyonException,BlockInfoException{Assert.assertTrue(mMasterInfo.mkdir("/testFolder"));Assert.assertTrue(mMasterInfo.mkdir("/testFolder/testFolder2"));int fileId=mMasterInfo.createFile("/testFolder/testFile",Constants.DEFAULT_BLOCK_SIZE_BYTE);int fileId2=mMasterInfo.createFile("/testFolder/testFolder2/testFile2",Constants.DEFAULT_BLOCK_SIZE_BYTE);Assert.assertEquals(2,mMasterInfo.getFileId("/testFolder"));Assert.assertEquals(3,mMasterInfo.getFileId("/testFolder/testFolder2"));Assert.assertEquals(fileId,mMasterInfo.getFileId("/testFolder/testFile"));Assert.assertEquals(fileId2,mMasterInfo.getFileId("/testFolder/testFolder2/testFile2"));Assert.assertFalse(mMasterInfo.delete(2,false));Assert.assertEquals(2,mMasterInfo.getFileId("/testFolder"));Assert.assertEquals(3,mMasterInfo.getFileId("/testFolder/testFolder2"));Assert.assertEquals(fileId,mMasterInfo.getFileId("/testFolder/testFile"));Assert.assertEquals(fileId2,mMasterInfo.getFileId("/testFolder/testFolder2/testFile2"));}
}