@Test public void testUserSpecifiedDotPath() throws Exception {
  System.setProperty("loader.path",".");
  PropertiesLauncher launcher=new PropertiesLauncher();
  assertEquals("[.]",ReflectionTestUtils.getField(launcher,"paths").toString());
}
