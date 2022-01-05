@Test public void testGetResource() throws Exception {
  File resFile=resMan.getResource(resourceFile.getName());
  assertTrue(resFile.exists());
  String resContent=FileUtils.readFileToString(resFile);
  assertEquals(resContent,RESOURCE_FILE_CONTENT);
}
