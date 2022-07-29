package tachyon;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

import tachyon.thrift.TachyonException;

/**
 * Unit tests for tachyon.InodeRawTable
 */
public class InodeRawTableTest {
  @Test
  public void setParentIdTest() throws TachyonException {
    InodeRawTable inode1 = new InodeRawTable("test1", 1, 0, 10, null, System.currentTimeMillis());
    Assert.assertEquals(0, inode1.getParentId());
    inode1.setParentId(2);
    Assert.assertEquals(2, inode1.getParentId());
  } 
}