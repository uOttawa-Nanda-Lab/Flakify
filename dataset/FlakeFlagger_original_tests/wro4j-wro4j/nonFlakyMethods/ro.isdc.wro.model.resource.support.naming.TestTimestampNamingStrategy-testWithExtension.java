@Test public void testWithExtension() throws Exception {
  final String result=namingStrategy.rename("fileName.js",null);
  assertEquals("fileName-" + TIMESTAMP + ".js",result);
}
