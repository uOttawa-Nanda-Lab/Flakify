package tachyon;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

import tachyon.thrift.TachyonException;

/**
 * Unit tests for tachyon.InodeRawTable
 */
public class InodeRawTableTest {
  @Test public void getMetadataTest() throws TachyonException{ByteBuffer metadata=TestUtils.getIncreasingIntBuffer(3);InodeRawTable inodeRawTable=new InodeRawTable("testTable1",1,0,10,metadata,System.currentTimeMillis());Assert.assertEquals(metadata,inodeRawTable.getMetadata());} 
}