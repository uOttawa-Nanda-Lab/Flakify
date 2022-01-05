@Test public void testServerCertGen() throws Exception {
  File serverCrt=new File(temp.getRoot().getAbsoluteFile() + File.separator + Configuration.SRVR_CRT_NAME_DEFAULT);
  assertTrue(serverCrt.exists());
}
