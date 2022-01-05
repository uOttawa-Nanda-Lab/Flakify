private void TableTest(ClientFileInfo fileInfo) throws IOException, InvalidPathException, FileDoesNotExistException {
  Journal journal=new Journal(MasterConf.get().JOURNAL_FOLDER,"image.data","log.data");
  MasterInfo info=new MasterInfo(new InetSocketAddress(9999),journal);
  Assert.assertEquals(12,info.ls("/",true).size());
  Assert.assertTrue(info.getFileId("/") != -1);
  Assert.assertTrue(info.getFileId("/xyz") != -1);
  Assert.assertEquals(fileInfo,info.getClientFileInfo(info.getFileId("/xyz")));
  info.stop();
}
