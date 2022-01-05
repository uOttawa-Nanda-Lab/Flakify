@Test public void handlesRegularExceptionWithoutMessage() throws Exception {
  willThrow(new NullPointerException()).given(this.regularCommand).run();
  int status=this.commandRunner.runAndHandleErrors("command");
  assertThat(status,equalTo(1));
  assertThat(this.calls,equalTo((Set<Call>)EnumSet.of(Call.ERROR_MESSAGE,Call.PRINT_STACK_TRACE)));
}
