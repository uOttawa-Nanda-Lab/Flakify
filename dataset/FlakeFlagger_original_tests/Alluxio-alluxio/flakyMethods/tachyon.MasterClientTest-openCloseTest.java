@Test public void openCloseTest() throws FileAlreadyExistException, InvalidPathException, TException, IOException {
  MasterClient masterClient=new MasterClient(mMasterInfo.getMasterAddress());
  Assert.assertFalse(masterClient.isConnected());
  masterClient.connect();
  Assert.assertTrue(masterClient.isConnected());
  masterClient.user_createFile("/file",Constants.DEFAULT_BLOCK_SIZE_BYTE);
  Assert.assertTrue(masterClient.user_getFileId("/file") != -1);
  masterClient.cleanConnect();
  Assert.assertFalse(masterClient.isConnected());
  masterClient.connect();
  Assert.assertTrue(masterClient.isConnected());
  Assert.assertTrue(masterClient.user_getFileId("/file") != -1);
}
