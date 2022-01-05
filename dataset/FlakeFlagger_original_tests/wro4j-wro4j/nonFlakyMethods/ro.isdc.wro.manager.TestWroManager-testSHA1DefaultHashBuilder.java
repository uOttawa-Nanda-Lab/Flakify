@Test public void testSHA1DefaultHashBuilder() throws Exception {
  final WroManager manager=managerFactory.create();
  final String path=manager.encodeVersionIntoGroupPath("g3",ResourceType.CSS,true);
  Assert.assertEquals("51e6de8dde498cb0bf082b2cd80323fca19eef5/g3.css?minimize=true",path);
}
