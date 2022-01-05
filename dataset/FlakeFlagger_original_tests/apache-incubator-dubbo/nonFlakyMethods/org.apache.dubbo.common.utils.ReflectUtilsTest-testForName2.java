@Test(expected=IllegalStateException.class) public void testForName2() throws Exception {
  ReflectUtils.forName("a.c.d.e.F");
}
