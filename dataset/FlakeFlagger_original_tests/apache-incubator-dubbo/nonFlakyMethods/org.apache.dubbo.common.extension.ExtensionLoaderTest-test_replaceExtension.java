@Test public void test_replaceExtension() throws Exception {
  try {
    ExtensionLoader.getExtensionLoader(AddExt1.class).getExtension("Manual2");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("No such extension org.apache.dubbo.common.extension.ext8_add.AddExt1 by name Manual"));
  }
{
    AddExt1 ext=ExtensionLoader.getExtensionLoader(AddExt1.class).getExtension("impl1");
    assertThat(ext,instanceOf(AddExt1Impl1.class));
    assertEquals("impl1",ExtensionLoader.getExtensionLoader(AddExt1.class).getExtensionName(AddExt1Impl1.class));
  }
{
    ExtensionLoader.getExtensionLoader(AddExt1.class).replaceExtension("impl1",AddExt1_ManualAdd2.class);
    AddExt1 ext=ExtensionLoader.getExtensionLoader(AddExt1.class).getExtension("impl1");
    assertThat(ext,instanceOf(AddExt1_ManualAdd2.class));
    assertEquals("impl1",ExtensionLoader.getExtensionLoader(AddExt1.class).getExtensionName(AddExt1_ManualAdd2.class));
  }
}
