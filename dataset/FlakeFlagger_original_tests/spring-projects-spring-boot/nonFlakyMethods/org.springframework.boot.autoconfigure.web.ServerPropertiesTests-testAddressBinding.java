@Test public void testAddressBinding() throws Exception {
  RelaxedDataBinder binder=new RelaxedDataBinder(this.properties,"server");
  binder.bind(new MutablePropertyValues(Collections.singletonMap("server.address","127.0.0.1")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertEquals(InetAddress.getByName("127.0.0.1"),this.properties.getAddress());
}
