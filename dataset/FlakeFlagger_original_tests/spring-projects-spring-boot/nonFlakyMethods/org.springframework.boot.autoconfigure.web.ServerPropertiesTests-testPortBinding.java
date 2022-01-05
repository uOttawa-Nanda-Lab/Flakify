@Test public void testPortBinding() throws Exception {
  new RelaxedDataBinder(this.properties,"server").bind(new MutablePropertyValues(Collections.singletonMap("server.port","9000")));
  assertEquals(9000,this.properties.getPort().intValue());
}
