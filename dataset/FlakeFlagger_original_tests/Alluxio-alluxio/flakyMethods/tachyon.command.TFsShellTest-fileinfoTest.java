@Test public void fileinfoTest() throws IOException {
  int fileId=TestUtils.createByteFile(mTfs,"/testFile",WriteType.MUST_CACHE,10);
  mFsShell.fileinfo(new String[]{"fileinfo","/testFile"});
  TachyonFile tFile=mTfs.getFile("/testFile");
  Assert.assertNotNull(tFile);
  List<ClientBlockInfo> blocks=mTfs.getFileBlocks(fileId);
  String[] commandParameters=new String[3 + blocks.size()];
  commandParameters[0]="fileinfo";
  commandParameters[1]="/testFile";
  commandParameters[2]=String.valueOf(fileId);
  Iterator<ClientBlockInfo> iter=blocks.iterator();
  int i=3;
  while (iter.hasNext()) {
    commandParameters[i++]=iter.next().toString();
  }
  Assert.assertEquals(getCommandOutput(commandParameters),mOutput.toString());
}
