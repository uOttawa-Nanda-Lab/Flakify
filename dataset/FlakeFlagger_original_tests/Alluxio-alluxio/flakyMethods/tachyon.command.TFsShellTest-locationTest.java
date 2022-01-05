@Test public void locationTest() throws IOException {
  int fileId=TestUtils.createByteFile(mTfs,"/testFile",WriteType.MUST_CACHE,10);
  mFsShell.location(new String[]{"location","/testFile"});
  TachyonFile tFile=mTfs.getFile("/testFile");
  Assert.assertNotNull(tFile);
  List<String> locationsList=tFile.getLocationHosts();
  String[] commandParameters=new String[3 + locationsList.size()];
  commandParameters[0]="location";
  commandParameters[1]="/testFile";
  commandParameters[2]=String.valueOf(fileId);
  Iterator<String> iter=locationsList.iterator();
  int i=3;
  while (iter.hasNext()) {
    commandParameters[i++]=iter.next();
  }
  Assert.assertEquals(getCommandOutput(commandParameters),mOutput.toString());
}
