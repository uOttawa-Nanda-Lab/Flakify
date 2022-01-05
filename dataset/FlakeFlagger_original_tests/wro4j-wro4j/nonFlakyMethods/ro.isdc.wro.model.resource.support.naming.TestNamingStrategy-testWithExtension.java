@Test public void testWithExtension() throws Exception {
  final String result=namingStrategy.rename("fileName.js",WroUtil.EMPTY_STREAM);
  Assert.assertEquals("fileName-" + HASH + ".js",result);
}
