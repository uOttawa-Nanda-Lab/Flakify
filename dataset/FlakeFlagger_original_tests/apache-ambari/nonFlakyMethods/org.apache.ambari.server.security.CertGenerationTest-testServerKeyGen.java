@Test public void testServerKeyGen() throws Exception {
  File serverKey=new File(temp.getRoot().getAbsoluteFile() + File.separator + Configuration.SRVR_KEY_NAME_DEFAULT);
  assertTrue(serverKey.exists());
}
