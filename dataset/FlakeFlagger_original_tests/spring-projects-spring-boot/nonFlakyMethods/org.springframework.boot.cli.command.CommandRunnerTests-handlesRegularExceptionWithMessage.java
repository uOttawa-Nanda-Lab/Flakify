@Test public void handlesRegularExceptionWithMessage() throws Exception {
  willThrow(new RuntimeException("With Message")).given(this.regularCommand).run();
  int status=this.commandRunner.runAndHandleErrors("command");
  assertThat(status,equalTo(1));
  assertThat(this.calls,equalTo((Set<Call>)EnumSet.of(Call.ERROR_MESSAGE)));
}
