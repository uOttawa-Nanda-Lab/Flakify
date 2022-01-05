@Test public void computeInodeIdTest(){
  Assert.assertEquals(1,BlockInfo.computeInodeId(1073741824));
  Assert.assertEquals(1,BlockInfo.computeInodeId(1073741825));
  Assert.assertEquals(1,BlockInfo.computeInodeId(2147483646));
  Assert.assertEquals(1,BlockInfo.computeInodeId(2147483647));
  Assert.assertEquals(3,BlockInfo.computeInodeId(3221225472L));
  Assert.assertEquals(3,BlockInfo.computeInodeId(3221225473L));
  Assert.assertEquals(3,BlockInfo.computeInodeId(4294967294L));
  Assert.assertEquals(3,BlockInfo.computeInodeId(4294967295L));
}
