@Test public void handlesNoSuchCommand() throws Exception {
  int status=this.commandRunner.runAndHandleErrors("missing");
  assertThat(status,equalTo(1));
  assertThat(this.calls,equalTo((Set<Call>)EnumSet.of(Call.ERROR_MESSAGE)));
}
