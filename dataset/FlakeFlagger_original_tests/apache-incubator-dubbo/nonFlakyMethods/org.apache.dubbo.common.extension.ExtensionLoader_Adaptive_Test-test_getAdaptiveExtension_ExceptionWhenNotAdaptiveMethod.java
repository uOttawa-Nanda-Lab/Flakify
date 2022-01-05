@Test public void test_getAdaptiveExtension_ExceptionWhenNotAdaptiveMethod() throws Exception {
  SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
  Map<String,String> map=new HashMap<String,String>();
  URL url=new URL("p1","1.2.3.4",1010,"path1",map);
  try {
    ext.bang(url,33);
    fail();
  }
 catch (  UnsupportedOperationException expected) {
    assertThat(expected.getMessage(),containsString("method "));
    assertThat(expected.getMessage(),containsString("of interface org.apache.dubbo.common.extension.ext1.SimpleExt is not adaptive method!"));
  }
}
