package tachyon;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

import tachyon.thrift.TachyonException;

/**
 * Unit tests for tachyon.InodeRawTable
 */
public class InodeRawTableTest {
  @Test public void getNullMetadataTest() throws TachyonException{InodeRawTable inodeRawTable=new InodeRawTable("testTable1",1,0,10,null,System.currentTimeMillis());Assert.assertTrue(inodeRawTable.getMetadata().equals(ByteBuffer.allocate(0)));} 
}