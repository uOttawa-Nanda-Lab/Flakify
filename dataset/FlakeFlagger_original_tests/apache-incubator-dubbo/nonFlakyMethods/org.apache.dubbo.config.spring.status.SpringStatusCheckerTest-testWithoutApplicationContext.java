@Test public void testWithoutApplicationContext(){
  Status status=springStatusChecker.check();
  assertThat(status.getLevel(),is(Status.Level.UNKNOWN));
}
