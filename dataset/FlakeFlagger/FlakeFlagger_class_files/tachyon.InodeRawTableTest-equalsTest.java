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
  public void equalsTest() throws TachyonException {
    InodeRawTable inode1 = new InodeRawTable("test1", 1, 0, 10, null, System.currentTimeMillis());
    InodeRawTable inode2 = new InodeRawTable("test2", 1, 0, 10, null, System.currentTimeMillis());
    InodeRawTable inode3 = new InodeRawTable("test3", 2, 0, 10, null, System.currentTimeMillis());
    Assert.assertTrue(inode1.equals(inode2));
    Assert.assertFalse(inode1.equals(inode3));
  } 
}