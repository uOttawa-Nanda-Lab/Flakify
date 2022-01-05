@Test public void basicTest() throws InvalidPathException, FileAlreadyExistException, TableColumnException, TableDoesNotExistException, FileDoesNotExistException, IOException, TException {
  int fileId=mTfs.createRawTable("/table",Constants.MAX_COLUMNS / 10);
  RawTable table=mTfs.getRawTable(fileId);
  for (int col=0; col < Constants.MAX_COLUMNS / 10; col++) {
    RawColumn column=table.getRawColumn(col);
    for (int pid=0; pid < 5; pid++) {
      Assert.assertTrue(column.createPartition(pid));
      TachyonFile file=column.getPartition(pid);
      Assert.assertEquals("/table" + Constants.PATH_SEPARATOR + MasterInfo.COL+ col+ Constants.PATH_SEPARATOR+ pid,file.getPath());
    }
    Assert.assertEquals(5,column.partitions());
  }
}
