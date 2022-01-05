@Test public void testNoExtension() throws Exception {
  final String result=namingStrategy.rename("fileName",null);
  assertEquals("fileName-" + TIMESTAMP,result);
}
