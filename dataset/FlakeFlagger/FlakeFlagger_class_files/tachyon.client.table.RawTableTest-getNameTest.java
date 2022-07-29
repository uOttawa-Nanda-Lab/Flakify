package tachyon.client.table;

import java.io.IOException;
import java.nio.ByteBuffer;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tachyon.Constants;
import tachyon.LocalTachyonCluster;
import tachyon.TestUtils;
import tachyon.client.OutStream;
import tachyon.client.TachyonByteBuffer;
import tachyon.client.TachyonFS;
import tachyon.client.TachyonFile;
import tachyon.client.WriteType;
import tachyon.client.table.RawColumn;
import tachyon.client.table.RawTable;

/**
 * Unit tests for tachyon.client.RawTable.
 */
public class RawTableTest {
  private LocalTachyonCluster mLocalTachyonCluster = null;
  private TachyonFS mTfs = null;

  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", "1000");
    mLocalTachyonCluster = new LocalTachyonCluster(10000);
    mLocalTachyonCluster.start();
    mTfs = mLocalTachyonCluster.getClient();
  }

  @Test public void getNameTest() throws IOException{for (int k=1;k < Constants.MAX_COLUMNS;k+=Constants.MAX_COLUMNS / 5){int fileId=mTfs.createRawTable("/x/table" + k,1);RawTable table=mTfs.getRawTable(fileId);Assert.assertEquals("table" + k,table.getName());table=mTfs.getRawTable("/x/table" + k);Assert.assertEquals("table" + k,table.getName());fileId=mTfs.createRawTable("/y/tab" + k,1,TestUtils.getIncreasingByteBuffer(k % 10));table=mTfs.getRawTable(fileId);Assert.assertEquals("tab" + k,table.getName());table=mTfs.getRawTable("/y/tab" + k);Assert.assertEquals("tab" + k,table.getName());}}
}
