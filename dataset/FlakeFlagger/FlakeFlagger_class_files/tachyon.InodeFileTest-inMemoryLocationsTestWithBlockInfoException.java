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
  @Test(expected=BlockInfoException.class) public void inMemoryLocationsTestWithBlockInfoException() throws IOException,BlockInfoException{InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));} 
}