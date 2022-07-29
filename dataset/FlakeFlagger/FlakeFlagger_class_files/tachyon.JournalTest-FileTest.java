package tachyon;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tachyon.client.TachyonFS;
import tachyon.client.TachyonFile;
import tachyon.client.WriteType;
import tachyon.conf.MasterConf;
import tachyon.thrift.ClientFileInfo;
import tachyon.thrift.FileDoesNotExistException;
import tachyon.thrift.InvalidPathException;

/**
 * Test master journal, including image and edit log.
 * Most tests will test edit log first, followed by the image.
 */
public class JournalTest {
  private LocalTachyonCluster mLocalTachyonCluster = null;
  private TachyonFS mTfs = null;

  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", "1000");
    mLocalTachyonCluster = new LocalTachyonCluster(10000);
    mLocalTachyonCluster.start();
    mTfs = mLocalTachyonCluster.getClient();
  }

  private void FileTestUtil(ClientFileInfo fileInfo) 
      throws IOException, InvalidPathException, FileDoesNotExistException {
    Journal journal = new Journal(MasterConf.get().JOURNAL_FOLDER, "image.data", "log.data");
    MasterInfo info = new MasterInfo(new InetSocketAddress(9999), journal);
    Assert.assertEquals(2, info.ls("/", true).size());
    Assert.assertTrue(info.getFileId("/") != -1);
    Assert.assertTrue(info.getFileId("/xyz") != -1);
    Assert.assertEquals(fileInfo, info.getClientFileInfo(info.getFileId("/xyz")));
    info.stop();
  }

  /**
 * Test files creation.
 * @throws Exception
 */@Test public void FileTest() throws Exception{mTfs.createFile("/xyz",64);ClientFileInfo fInfo=mLocalTachyonCluster.getMasterInfo().getClientFileInfo("/xyz");mLocalTachyonCluster.stop();FileTestUtil(fInfo);String editLogPath=mLocalTachyonCluster.getEditLogPath();UnderFileSystem.get(editLogPath).delete(editLogPath,true);FileTestUtil(fInfo);}

  private void ManyFileTestUtil() 
      throws IOException, InvalidPathException, FileDoesNotExistException {
    Journal journal = new Journal(MasterConf.get().JOURNAL_FOLDER, "image.data", "log.data");
    MasterInfo info = new MasterInfo(new InetSocketAddress(9999), journal);
    Assert.assertEquals(11, info.ls("/", true).size());
    Assert.assertTrue(info.getFileId("/") != -1);
    for (int k = 0; k < 10; k ++) {
      Assert.assertTrue(info.getFileId("/a" + k) != -1);
    }
    info.stop();
  }

  private void FileFolderUtil() 
      throws IOException, InvalidPathException, FileDoesNotExistException {
    Journal journal = new Journal(MasterConf.get().JOURNAL_FOLDER, "image.data", "log.data");
    MasterInfo info = new MasterInfo(new InetSocketAddress(9999), journal);
    Assert.assertEquals(111, info.ls("/", true).size());
    Assert.assertTrue(info.getFileId("/") != -1);
    for (int i = 0; i < 10; i ++) {
      for (int j = 0; j < 10; j ++) {
        Assert.assertTrue(info.getFileId("/i" + i + "/j" + j) != -1);
      }
    }
    info.stop();
  }

  private void RenameTestUtil() 
      throws IOException, InvalidPathException, FileDoesNotExistException {
    Journal journal = new Journal(MasterConf.get().JOURNAL_FOLDER, "image.data", "log.data");
    MasterInfo info = new MasterInfo(new InetSocketAddress(9999), journal);
    Assert.assertEquals(111, info.ls("/", true).size());
    Assert.assertTrue(info.getFileId("/") != -1);
    for (int i = 0; i < 10; i ++) {
      for (int j = 0; j < 10; j ++) {
        Assert.assertTrue(info.getFileId("/ii" + i + "/jj" + j) != -1);
      }
    }
    info.stop();
  }

  private void DeleteTestUtil() 
      throws IOException, InvalidPathException, FileDoesNotExistException {
    Journal journal = new Journal(MasterConf.get().JOURNAL_FOLDER, "image.data", "log.data");
    MasterInfo info = new MasterInfo(new InetSocketAddress(9999), journal);
    Assert.assertEquals(31, info.ls("/", true).size());
    Assert.assertTrue(info.getFileId("/") != -1);
    for (int i = 0; i < 5; i ++) {
      for (int j = 0; j < 5; j ++) {
        Assert.assertTrue(info.getFileId("/i" + i + "/j" + j) != -1);
      }
    }
    info.stop();
  }

  private void AddBlockTestUtil(ClientFileInfo fileInfo) 
      throws IOException, InvalidPathException, FileDoesNotExistException {
    Journal journal = new Journal(MasterConf.get().JOURNAL_FOLDER, "image.data", "log.data");
    MasterInfo info = new MasterInfo(new InetSocketAddress(9999), journal);
    Assert.assertEquals(2, info.ls("/", true).size());
    Assert.assertTrue(info.getFileId("/") != -1);
    Assert.assertTrue(info.getFileId("/xyz") != -1);
    fileInfo.setInMemory(false);
    Assert.assertEquals(fileInfo, info.getClientFileInfo(info.getFileId("/xyz")));
    info.stop();
  }

  private void AddCheckpointTestUtil(ClientFileInfo fileInfo, ClientFileInfo ckFileInfo) 
      throws IOException, InvalidPathException, FileDoesNotExistException {
    Journal journal = new Journal(MasterConf.get().JOURNAL_FOLDER, "image.data", "log.data");
    MasterInfo info = new MasterInfo(new InetSocketAddress(9999), journal);
    Assert.assertEquals(3, info.ls("/", true).size());
    Assert.assertTrue(info.getFileId("/") != -1);
    Assert.assertTrue(info.getFileId("/xyz") != -1);
    Assert.assertTrue(info.getFileId("/xyz_ck") != -1);
    Assert.assertEquals(fileInfo, info.getClientFileInfo(info.getFileId("/xyz")));
    Assert.assertEquals(ckFileInfo, info.getClientFileInfo(info.getFileId("/xyz_ck")));
    info.stop();
  }
}
