@Test public void up() throws Exception {
  Health health=new Health.Builder().up().build();
  assertThat(health.getStatus(),equalTo(Status.UP));
  assertThat(health.getDetails().size(),equalTo(0));
}
