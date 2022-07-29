package tachyon;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

import tachyon.thrift.TachyonException;

/**
 * Unit tests for tachyon.InodeRawTable
 */
public class InodeRawTableTest {
  @Test public void updateMetadataTest() throws TachyonException{InodeRawTable inodeRawTable=new InodeRawTable("testTable1",1,0,10,null,System.currentTimeMillis());Assert.assertEquals(ByteBuffer.allocate(0),inodeRawTable.getMetadata());ByteBuffer metadata=TestUtils.getIncreasingIntBuffer(7);inodeRawTable.updateMetadata(metadata);Assert.assertEquals(metadata,inodeRawTable.getMetadata());} 
}