/** 
 * Test push only router
 */
@Test public void testNotified_Normal_withRouters(){
  LogUtil.start();
  RegistryDirectory registryDirectory=getRegistryDirectory();
  test_Notified1invokers(registryDirectory);
  test_Notified_only_routers(registryDirectory);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  Assert.assertTrue("notify no invoker urls ,should not error",LogUtil.checkNoError());
  LogUtil.stop();
  test_Notified2invokers(registryDirectory);
}
