@Test public void testDestroy_WithDestroyRegistry_WithError(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  registryDirectory.setRegistry(new MockRegistry(true));
  registryDirectory.destroy();
}
