@Test public void testMD5Fingerprint() throws Exception {
  final WroManagerFactory factory=new BaseWroManagerFactory().setModelFactory(getValidModelFactory()).setHashStrategy(new MD5HashStrategy());
  final WroManager manager=factory.create();
  final String path=manager.encodeVersionIntoGroupPath("g3",ResourceType.CSS,true);
  Assert.assertEquals("42b98f2980dc1366cf1d2677d4891eda/g3.css?minimize=true",path);
}
