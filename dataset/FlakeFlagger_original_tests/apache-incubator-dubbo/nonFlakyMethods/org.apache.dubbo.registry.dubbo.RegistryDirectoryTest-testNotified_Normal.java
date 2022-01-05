@Test public void testNotified_Normal(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  test_Notified2invokers(registryDirectory);
  test_Notified1invokers(registryDirectory);
  test_Notified3invokers(registryDirectory);
  testforbid(registryDirectory);
}
