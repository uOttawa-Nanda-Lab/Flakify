@Test public void unknown() throws Exception {
  Health health=new Health.Builder().unknown().build();
  assertThat(health.getStatus(),equalTo(Status.UNKNOWN));
  assertThat(health.getDetails().size(),equalTo(0));
}
