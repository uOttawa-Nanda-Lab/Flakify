@Test public void shouldCreateRegistry(){
  Registry registry=new MulticastRegistryFactory().createRegistry(URL.valueOf("multicast://239.255.255.255/"));
  assertThat(registry,not(nullValue()));
  assertThat(registry.isAvailable(),is(true));
}
