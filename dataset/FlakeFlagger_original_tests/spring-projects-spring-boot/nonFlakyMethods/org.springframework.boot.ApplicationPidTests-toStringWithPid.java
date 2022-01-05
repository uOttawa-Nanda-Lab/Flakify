@Test public void toStringWithPid() throws Exception {
  assertThat(new ApplicationPid("123").toString(),equalTo("123"));
}
