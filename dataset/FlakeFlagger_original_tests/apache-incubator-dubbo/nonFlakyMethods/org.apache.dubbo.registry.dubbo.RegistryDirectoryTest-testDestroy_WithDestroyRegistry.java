@Test public void testDestroy_WithDestroyRegistry(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  CountDownLatch latch=new CountDownLatch(1);
  registryDirectory.setRegistry(new MockRegistry(latch));
  registryDirectory.subscribe(URL.valueOf("consumer://" + NetUtils.getLocalHost() + "/DemoService?category=providers"));
  registryDirectory.destroy();
  Assert.assertEquals(0,latch.getCount());
}
