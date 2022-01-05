@Test public void test_getExtensionLoader_Null() throws Exception {
  try {
    ExtensionLoader.getExtensionLoader(null);
    fail();
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),containsString("Extension type == null"));
  }
}
