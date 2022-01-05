@Test public void assertCreateCoordinatorRegistryCenterWithoutDigest() throws ReflectiveOperationException {
  ZookeeperConfiguration zkConfig=getZookeeperConfiguration(RegistryCenterFactory.createCoordinatorRegistryCenter(getConnectionString(),"namespace",Optional.<String>absent()));
  assertThat(zkConfig.getNamespace(),is("namespace"));
  assertNull(zkConfig.getDigest());
}
