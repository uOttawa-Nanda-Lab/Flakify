@Test public void test_getExtensionLoader_NotSpiAnnotation() throws Exception {
  try {
    ExtensionLoader.getExtensionLoader(NoSpiExt.class);
    fail();
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),allOf(containsString("org.apache.dubbo.common.extension.NoSpiExt"),containsString("is not extension"),containsString("WITHOUT @SPI Annotation")));
  }
}
