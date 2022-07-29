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
  public void setNameTest() throws TachyonException {
    InodeRawTable inode1 = new InodeRawTable("test1", 1, 0, 10, null, System.currentTimeMillis());
    Assert.assertEquals("test1", inode1.getName());
    inode1.setName("test2");
    Assert.assertEquals("test2", inode1.getName());
  } 
}