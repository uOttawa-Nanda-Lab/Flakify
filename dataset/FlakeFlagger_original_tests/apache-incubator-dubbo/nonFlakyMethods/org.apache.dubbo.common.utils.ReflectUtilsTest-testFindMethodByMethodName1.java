@Test public void testFindMethodByMethodName1() throws Exception {
  assertNotNull(ReflectUtils.findMethodByMethodName(Foo.class,"hello"));
}
