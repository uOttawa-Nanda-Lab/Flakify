@Test public void handlesSuccess() throws Exception {
  int status=this.commandRunner.runAndHandleErrors("command");
  assertThat(status,equalTo(0));
  assertThat(this.calls,equalTo((Set<Call>)EnumSet.noneOf(Call.class)));
}
