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

  @Test public void rawtablePerfTest() throws IOException{int col=200;long sMs=System.currentTimeMillis();int fileId=mTfs.createRawTable("/table",col);sMs=System.currentTimeMillis();RawTable table=mTfs.getRawTable(fileId);Assert.assertEquals(col,table.getColumns());table=mTfs.getRawTable("/table");Assert.assertEquals(col,table.getColumns());sMs=System.currentTimeMillis();for (int k=0;k < col;k++){RawColumn rawCol=table.getRawColumn(k);rawCol.createPartition(0);TachyonFile file=rawCol.getPartition(0);OutStream outStream=file.getOutStream(WriteType.MUST_CACHE);outStream.write(TestUtils.getIncreasingByteArray(10));outStream.close();}sMs=System.currentTimeMillis();for (int k=0;k < col;k++){RawColumn rawCol=table.getRawColumn(k);TachyonFile file=rawCol.getPartition(0,true);TachyonByteBuffer buf=file.readByteBuffer();Assert.assertEquals(TestUtils.getIncreasingByteBuffer(10),buf.DATA);buf.close();}sMs=System.currentTimeMillis();for (int k=0;k < col;k++){RawColumn rawCol=table.getRawColumn(k);TachyonFile file=rawCol.getPartition(0,true);TachyonByteBuffer buf=file.readByteBuffer();Assert.assertEquals(TestUtils.getIncreasingByteBuffer(10),buf.DATA);buf.close();}}
}
