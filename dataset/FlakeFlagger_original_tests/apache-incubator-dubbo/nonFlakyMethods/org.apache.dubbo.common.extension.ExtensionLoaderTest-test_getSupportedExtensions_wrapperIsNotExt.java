@Test public void test_getSupportedExtensions_wrapperIsNotExt() throws Exception {
  Set<String> exts=ExtensionLoader.getExtensionLoader(WrappedExt.class).getSupportedExtensions();
  Set<String> expected=new HashSet<String>();
  expected.add("impl1");
  expected.add("impl2");
  assertEquals(expected,exts);
}
