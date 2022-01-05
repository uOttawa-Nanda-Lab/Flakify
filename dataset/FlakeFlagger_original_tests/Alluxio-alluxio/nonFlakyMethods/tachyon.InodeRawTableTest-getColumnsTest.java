@Test public void getColumnsTest() throws TachyonException {
  InodeRawTable inodeRawTable=new InodeRawTable("testTable1",1,0,10,null,System.currentTimeMillis());
  Assert.assertEquals(10,inodeRawTable.getColumns());
}
