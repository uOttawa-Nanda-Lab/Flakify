@Test public void getPidFromJvm() throws Exception {
  assertThat(new ApplicationPid().toString(),not(isEmptyOrNullString()));
}
