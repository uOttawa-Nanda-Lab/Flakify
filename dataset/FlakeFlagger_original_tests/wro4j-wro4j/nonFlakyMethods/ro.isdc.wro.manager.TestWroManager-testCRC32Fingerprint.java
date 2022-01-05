@Test public void testCRC32Fingerprint() throws Exception {
  final WroManagerFactory factory=new BaseWroManagerFactory().setModelFactory(getValidModelFactory()).setHashStrategy(new CRC32HashStrategy());
  final WroManager manager=factory.create();
  final String path=manager.encodeVersionIntoGroupPath("g3",ResourceType.CSS,true);
  assertEquals("daa1bb3c/g3.css?minimize=true",path);
}
