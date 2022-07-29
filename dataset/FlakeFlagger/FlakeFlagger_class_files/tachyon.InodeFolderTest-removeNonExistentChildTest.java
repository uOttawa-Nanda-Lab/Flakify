package tachyon;

import java.util.Map;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for tachyon.InodeFolder
 */
public class InodeFolderTest {
  @Test public void removeNonExistentChildTest(){InodeFolder inodeFolder=new InodeFolder("testFolder1",1,0,System.currentTimeMillis());inodeFolder.addChild(2);Assert.assertEquals(1,inodeFolder.getNumberOfChildren());inodeFolder.removeChild(3);Assert.assertEquals(1,inodeFolder.getNumberOfChildren());} 
}
