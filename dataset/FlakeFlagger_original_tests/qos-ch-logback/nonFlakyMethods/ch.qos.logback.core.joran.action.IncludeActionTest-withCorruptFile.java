@Test public void withCorruptFile() throws JoranException, IOException {
  String tmpOut=copyToTemp(INVALID);
  System.setProperty(INCLUDE_KEY,tmpOut);
  tc.doConfigure(TOP_BY_FILE);
  assertEquals(Status.ERROR,statusChecker.getHighestLevel(0));
  assertTrue(statusChecker.containsException(SAXParseException.class));
  File f=new File(tmpOut);
  assertTrue(f.exists());
  assertTrue(f.delete());
}
