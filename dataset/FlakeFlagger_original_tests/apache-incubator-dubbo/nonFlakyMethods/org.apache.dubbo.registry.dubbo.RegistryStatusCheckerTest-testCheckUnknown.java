@Test public void testCheckUnknown(){
  assertEquals(Status.Level.UNKNOWN,new RegistryStatusChecker().check().getLevel());
}
