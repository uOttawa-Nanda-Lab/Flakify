@Test public void assertGetIp(){
  assertThat(new JobInstance().getIp(),Is.is(IpUtils.getIp()));
}
