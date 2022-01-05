@Test(expected=IllegalStateException.class) public void testFindMethodByMethodName2() throws Exception {
  ReflectUtils.findMethodByMethodName(Foo2.class,"hello");
}
