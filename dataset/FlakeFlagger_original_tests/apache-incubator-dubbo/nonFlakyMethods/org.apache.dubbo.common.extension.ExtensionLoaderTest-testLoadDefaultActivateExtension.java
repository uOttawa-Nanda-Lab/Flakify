@Test public void testLoadDefaultActivateExtension() throws Exception {
  URL url=URL.valueOf("test://localhost/test?ext=order1,default");
  List<ActivateExt1> list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,"ext","default_group");
  Assert.assertEquals(2,list.size());
  Assert.assertTrue(list.get(0).getClass() == OrderActivateExtImpl1.class);
  Assert.assertTrue(list.get(1).getClass() == ActivateExt1Impl1.class);
  url=URL.valueOf("test://localhost/test?ext=default,order1");
  list=ExtensionLoader.getExtensionLoader(ActivateExt1.class).getActivateExtension(url,"ext","default_group");
  Assert.assertEquals(2,list.size());
  Assert.assertTrue(list.get(0).getClass() == ActivateExt1Impl1.class);
  Assert.assertTrue(list.get(1).getClass() == OrderActivateExtImpl1.class);
}
