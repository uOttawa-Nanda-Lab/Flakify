@Test public void computeBlockIdTest(){
  Assert.assertEquals(1073741824,BlockInfo.computeBlockId(1,0));
  Assert.assertEquals(1073741825,BlockInfo.computeBlockId(1,1));
  Assert.assertEquals(2147483646,BlockInfo.computeBlockId(1,1073741822));
  Assert.assertEquals(2147483647,BlockInfo.computeBlockId(1,1073741823));
  Assert.assertEquals(3221225472L,BlockInfo.computeBlockId(3,0));
  Assert.assertEquals(3221225473L,BlockInfo.computeBlockId(3,1));
  Assert.assertEquals(4294967294L,BlockInfo.computeBlockId(3,1073741822));
  Assert.assertEquals(4294967295L,BlockInfo.computeBlockId(3,1073741823));
}
