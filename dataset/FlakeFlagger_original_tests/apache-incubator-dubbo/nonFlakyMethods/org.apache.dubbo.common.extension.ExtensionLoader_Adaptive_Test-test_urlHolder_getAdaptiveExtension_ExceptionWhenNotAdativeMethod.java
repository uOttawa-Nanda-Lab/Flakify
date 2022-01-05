@Test public void test_urlHolder_getAdaptiveExtension_ExceptionWhenNotAdativeMethod() throws Exception {
  Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getAdaptiveExtension();
  Map<String,String> map=new HashMap<String,String>();
  URL url=new URL("p1","1.2.3.4",1010,"path1",map);
  try {
    ext.bang(url,33);
    fail();
  }
 catch (  UnsupportedOperationException expected) {
    assertThat(expected.getMessage(),containsString("method "));
    assertThat(expected.getMessage(),containsString("of interface org.apache.dubbo.common.extension.ext2.Ext2 is not adaptive method!"));
  }
}
