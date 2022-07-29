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
  @Test public void inMemoryTest() throws BlockInfoException{InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());inodeFile.addBlock(new BlockInfo(inodeFile,0,5));Assert.assertFalse(inodeFile.isFullyInMemory());inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));Assert.assertTrue(inodeFile.isFullyInMemory());inodeFile.removeLocation(0,1);Assert.assertFalse(inodeFile.isFullyInMemory());inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));Assert.assertTrue(inodeFile.isFullyInMemory());inodeFile.removeLocation(0,1);Assert.assertFalse(inodeFile.isFullyInMemory());inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));inodeFile.addLocation(0,2,new NetAddress("testhost1",1000));Assert.assertTrue(inodeFile.isFullyInMemory());inodeFile.removeLocation(0,1);Assert.assertTrue(inodeFile.isFullyInMemory());} 
}