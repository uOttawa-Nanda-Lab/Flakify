@Test public void testServerKeystoreGen() throws Exception {
  File serverKeyStrore=new File(temp.getRoot().getAbsoluteFile() + File.separator + Configuration.KSTR_NAME_DEFAULT);
  assertTrue(serverKeyStrore.exists());
}
