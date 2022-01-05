@Test public void assertCreateCoordinatorRegistryCenterWithDigest() throws ReflectiveOperationException {
  ZookeeperConfiguration zkConfig=getZookeeperConfiguration(RegistryCenterFactory.createCoordinatorRegistryCenter(getConnectionString(),"namespace",Optional.of("digest")));
  assertThat(zkConfig.getNamespace(),is("namespace"));
  assertThat(zkConfig.getDigest(),is("digest"));
}
