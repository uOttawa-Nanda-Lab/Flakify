package tachyon.client;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tachyon.LocalTachyonCluster;
import tachyon.TestUtils;

public class LocalBlockInStreamTest {
  private final int MIN_LEN = 0;
  private final int MAX_LEN = 255;
  private final int DELTA = 33;

  private LocalTachyonCluster mLocalTachyonCluster = null;
  private TachyonFS mTfs = null;
  private Set<WriteType> mWriteCacheType;

  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", "1000");
    mLocalTachyonCluster = new LocalTachyonCluster(10000);
    mLocalTachyonCluster.start();
    mTfs = mLocalTachyonCluster.getClient();

    mWriteCacheType = new HashSet<WriteType>();
    mWriteCacheType.add(WriteType.MUST_CACHE);
    mWriteCacheType.add(WriteType.CACHE_THROUGH);
  }

  /**
   * Test <code>void read(byte[] b, int off, int len)</code>.
   */
  @Test
  public void readTest3() throws IOException {
    for (int k = MIN_LEN; k <= MAX_LEN; k += DELTA) {
      for (WriteType op : mWriteCacheType) {
        int fileId = TestUtils.createByteFile(mTfs, "/root/testFile_" + k + "_" + op, op, k);

        TachyonFile file = mTfs.getFile(fileId);
        InStream is = file.getInStream(ReadType.NO_CACHE);
        if (k == 0) {
          Assert.assertTrue(is instanceof EmptyBlockInStream);
        } else {
          Assert.assertTrue(is instanceof LocalBlockInStream);
        }
        byte[] ret = new byte[k / 2];
        Assert.assertEquals(k / 2, is.read(ret, 0, k / 2));
        Assert.assertTrue(TestUtils.equalIncreasingByteArray(k / 2, ret));
        is.close();
        Assert.assertTrue(file.isInMemory());

        is = file.getInStream(ReadType.CACHE);
        if (k == 0) {
          Assert.assertTrue(is instanceof EmptyBlockInStream);
        } else {
          Assert.assertTrue(is instanceof LocalBlockInStream);
        }
        ret = new byte[k];
        Assert.assertEquals(k, is.read(ret, 0, k));
        Assert.assertTrue(TestUtils.equalIncreasingByteArray(k, ret));
        is.close();
        Assert.assertTrue(file.isInMemory());
      }
    }
  }
}
