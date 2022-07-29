package tachyon;

import java.util.Map;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for tachyon.InodeFolder
 */
public class InodeFolderTest {
  @Test public void sameIdChildrenTest(){InodeFolder inodeFolder=new InodeFolder("testFolder1",1,0,System.currentTimeMillis());inodeFolder.addChild(2);inodeFolder.addChild(2);Assert.assertTrue(inodeFolder.getChildrenIds().get(0) == 2);Assert.assertEquals(1,inodeFolder.getNumberOfChildren());} 
}
