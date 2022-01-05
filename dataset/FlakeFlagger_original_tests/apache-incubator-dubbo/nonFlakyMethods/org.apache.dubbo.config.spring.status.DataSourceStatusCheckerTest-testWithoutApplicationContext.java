@Test public void testWithoutApplicationContext(){
  Status status=dataSourceStatusChecker.check();
  assertThat(status.getLevel(),is(Status.Level.UNKNOWN));
}
