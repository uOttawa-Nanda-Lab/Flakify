@Test public void assertCreateCoordinatorRegistryCenterFromCache() throws ReflectiveOperationException {
  RegistryCenterFactory.createCoordinatorRegistryCenter(getConnectionString(),"otherNamespace",Optional.<String>absent());
  ZookeeperConfiguration zkConfig=getZookeeperConfiguration(RegistryCenterFactory.createCoordinatorRegistryCenter(getConnectionString(),"otherNamespace",Optional.<String>absent()));
  assertThat(zkConfig.getNamespace(),is("otherNamespace"));
  assertNull(zkConfig.getDigest());
}
