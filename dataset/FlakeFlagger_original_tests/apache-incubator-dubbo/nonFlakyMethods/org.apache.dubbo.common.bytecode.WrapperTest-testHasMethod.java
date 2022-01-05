@Test public void testHasMethod() throws Exception {
  Wrapper w=Wrapper.getWrapper(I1.class);
  Assert.assertTrue(w.hasMethod("setName"));
  Assert.assertTrue(w.hasMethod("hello"));
  Assert.assertTrue(w.hasMethod("showInt"));
  Assert.assertTrue(w.hasMethod("getFloat"));
  Assert.assertTrue(w.hasMethod("setFloat"));
  Assert.assertFalse(w.hasMethod("setFloatXXX"));
}
