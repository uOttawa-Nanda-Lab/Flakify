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
  @Test public void inodeLengthTest() throws SuspectedFileSizeException,BlockInfoException{InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());long testLength=100;inodeFile.setLength(testLength);Assert.assertEquals(testLength,inodeFile.getLength());} 
}