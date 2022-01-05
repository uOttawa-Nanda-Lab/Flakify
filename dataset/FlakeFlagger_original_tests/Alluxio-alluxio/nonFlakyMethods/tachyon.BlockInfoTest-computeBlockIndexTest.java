@Test public void computeBlockIndexTest(){
  Assert.assertEquals(0,BlockInfo.computeBlockIndex(1073741824));
  Assert.assertEquals(1,BlockInfo.computeBlockIndex(1073741825));
  Assert.assertEquals(1073741822,BlockInfo.computeBlockIndex(2147483646));
  Assert.assertEquals(1073741823,BlockInfo.computeBlockIndex(2147483647));
  Assert.assertEquals(0,BlockInfo.computeBlockIndex(3221225472L));
  Assert.assertEquals(1,BlockInfo.computeBlockIndex(3221225473L));
  Assert.assertEquals(1073741822,BlockInfo.computeBlockIndex(4294967294L));
  Assert.assertEquals(1073741823,BlockInfo.computeBlockIndex(4294967295L));
}
