@Test public void should_exception_when_no_keyspace_name_set() throws Exception {
  factory.setEntityPackages("com.test");
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("Keyspace name should be provided");
  factory.initialize();
}
