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

  @Test public void lsTest() throws FileAlreadyExistException,InvalidPathException,TachyonException,BlockInfoException,FileDoesNotExistException{for (int i=0;i < 10;i++){mMasterInfo.mkdir("/i" + i);for (int j=0;j < 10;j++){mMasterInfo.createFile("/i" + i + "/j" + j,64);}}Assert.assertEquals(1,mMasterInfo.ls("/i0/j0",false).size());Assert.assertEquals(1,mMasterInfo.ls("/i0/j0",true).size());for (int i=0;i < 10;i++){Assert.assertEquals(11,mMasterInfo.ls("/i" + i,false).size());Assert.assertEquals(11,mMasterInfo.ls("/i" + i,true).size());}Assert.assertEquals(11,mMasterInfo.ls("/",false).size());Assert.assertEquals(111,mMasterInfo.ls("/",true).size());}
}