@Test public void testExtensionFactory(){
  try {
    ExtensionFactory factory=ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getExtension("myfactory");
    Assert.assertTrue(factory instanceof ExtensionFactory);
    Assert.assertTrue(factory instanceof com.alibaba.dubbo.common.extension.ExtensionFactory);
    Assert.assertTrue(factory instanceof MyExtensionFactory);
    ExtensionFactory spring=ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getExtension("spring");
    Assert.assertTrue(spring instanceof ExtensionFactory);
    Assert.assertFalse(spring instanceof com.alibaba.dubbo.common.extension.ExtensionFactory);
  }
 catch (  IllegalArgumentException expected) {
    fail();
  }
}
