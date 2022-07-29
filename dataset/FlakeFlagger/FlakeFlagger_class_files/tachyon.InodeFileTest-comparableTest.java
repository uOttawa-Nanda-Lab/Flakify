package tachyon;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import tachyon.thrift.BlockInfoException;
import tachyon.thrift.NetAddress;
import tachyon.thrift.SuspectedFileSizeException;

/**
 * Unit tests for tachyon.InodeFile
 */
public class InodeFileTest {
  @Test public void comparableTest(){InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());InodeFile inode2=new InodeFile("test2",2,0,1000,System.currentTimeMillis());Assert.assertEquals(-1,inode1.compareTo(inode2));Assert.assertEquals(0,inode1.compareTo(inode1));Assert.assertEquals(0,inode2.compareTo(inode2));Assert.assertEquals(1,inode2.compareTo(inode1));} 
}