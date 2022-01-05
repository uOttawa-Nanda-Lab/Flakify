@Test public void testNoExtension() throws Exception {
  final String result=namingStrategy.rename("fileName",WroUtil.EMPTY_STREAM);
  Assert.assertEquals("fileName-" + HASH,result);
}
