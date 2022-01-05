@Test public void test_getSupportedExtensions() throws Exception {
  Set<String> exts=ExtensionLoader.getExtensionLoader(SimpleExt.class).getSupportedExtensions();
  Set<String> expected=new HashSet<String>();
  expected.add("impl1");
  expected.add("impl2");
  expected.add("impl3");
  assertEquals(expected,exts);
}
