@Test public void handlesExceptionWithDashDashDebug() throws Exception {
  willThrow(new RuntimeException()).given(this.regularCommand).run();
  int status=this.commandRunner.runAndHandleErrors("command","--debug");
  assertEquals("true",System.getProperty("debug"));
  assertThat(status,equalTo(1));
  assertThat(this.calls,equalTo((Set<Call>)EnumSet.of(Call.ERROR_MESSAGE,Call.PRINT_STACK_TRACE)));
}
