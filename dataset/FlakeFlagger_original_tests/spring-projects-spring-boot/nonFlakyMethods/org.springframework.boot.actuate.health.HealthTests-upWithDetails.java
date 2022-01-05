@Test public void upWithDetails() throws Exception {
  Health health=new Health.Builder().up().withDetail("a","b").build();
  assertThat(health.getStatus(),equalTo(Status.UP));
  assertThat(health.getDetails().get("a"),equalTo((Object)"b"));
}
