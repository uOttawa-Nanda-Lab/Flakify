@Test public void testLoadActivateExtension() throws Exception {
  URL url=URL.valueOf("test://localhost/test");
  List<ActivateExt1> list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"default_group");
  Assert.assertEquals(1,list.size());
  Assert.assertTrue(list.get(0).getClass() == ActivateExt1Impl1.class);
  url=url.addParameter(Constants.GROUP_KEY,"group1");
  list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"group1");
  Assert.assertEquals(1,list.size());
  Assert.assertTrue(list.get(0).getClass() == GroupActivateExtImpl.class);
  url=url.addParameter(Constants.GROUP_KEY,"old_group");
  list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"old_group");
  Assert.assertEquals(2,list.size());
  Assert.assertTrue(list.get(0).getClass() == OldActivateExt1Impl2.class || list.get(0).getClass() == OldActivateExt1Impl3.class);
  url=url.removeParameter(Constants.GROUP_KEY);
  url=url.addParameter(Constants.GROUP_KEY,"value");
  url=url.addParameter("value","value");
  list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"value");
  Assert.assertEquals(1,list.size());
  Assert.assertTrue(list.get(0).getClass() == ValueActivateExtImpl.class);
  url=URL.valueOf("test://localhost/test");
  url=url.addParameter(Constants.GROUP_KEY,"order");
  list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,new String[]{},"order");
  Assert.assertEquals(2,list.size());
  Assert.assertTrue(list.get(0).getClass() == OrderActivateExtImpl1.class);
  Assert.assertTrue(list.get(1).getClass() == OrderActivateExtImpl2.class);
}
