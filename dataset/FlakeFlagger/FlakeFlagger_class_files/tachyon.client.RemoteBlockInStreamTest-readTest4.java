package tachyon.client;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tachyon.LocalTachyonCluster;
import tachyon.TestUtils;

public class RemoteBlockInStreamTest {
  private final int MIN_LEN = 0;
  private final int MAX_LEN = 255;
  private final int DELTA = 33;

  private LocalTachyonCluster mLocalTachyonCluster = null;
  private TachyonFS mTfs = null;

  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", "1000");
    System.setProperty("tachyon.user.remote.read.buffer.size.byte", "100");
    mLocalTachyonCluster = new LocalTachyonCluster(10000);
    mLocalTachyonCluster.start();
    mTfs = mLocalTachyonCluster.getClient();
  }

  /**
 * Test <code>void read()</code>. Read from remote data server.
 */@Test public void readTest4() throws IOException{for (int k=MIN_LEN + DELTA;k <= MAX_LEN;k+=DELTA){WriteType op=WriteType.MUST_CACHE;int fileId=TestUtils.createByteFile(mTfs,"/root/testFile_" + k + "_" + op,op,k);TachyonFile file=mTfs.getFile(fileId);RemoteBlockInStream is=new RemoteBlockInStream(file,ReadType.NO_CACHE,0);Assert.assertTrue(is instanceof RemoteBlockInStream);byte[] ret=new byte[k];int value=is.read();int cnt=0;while (value != -1){ret[cnt++]=(byte)value;value=is.read();}Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));is.close();Assert.assertTrue(file.isInMemory());}}
}
