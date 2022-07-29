package tachyon;

import java.util.Map;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for tachyon.InodeFolder
 */
public class InodeFolderTest {
  //Tests for Inode methods
  @Test
  public void comparableTest() {
    InodeFolder inode1 = new InodeFolder("test1", 1, 0, System.currentTimeMillis());
    InodeFolder inode2 = new InodeFolder("test2", 2, 0, System.currentTimeMillis());
    Assert.assertEquals(-1, inode1.compareTo(inode2));
  } 
}
