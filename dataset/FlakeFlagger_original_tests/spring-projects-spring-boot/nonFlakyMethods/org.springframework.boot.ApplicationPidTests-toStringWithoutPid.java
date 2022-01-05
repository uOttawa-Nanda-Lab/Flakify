@Test public void toStringWithoutPid() throws Exception {
  assertThat(new ApplicationPid(null).toString(),equalTo("???"));
}
