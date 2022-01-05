@Test public void unknownWithDetails() throws Exception {
  Health health=new Health.Builder().unknown().withDetail("a","b").build();
  assertThat(health.getStatus(),equalTo(Status.UNKNOWN));
  assertThat(health.getDetails().get("a"),equalTo((Object)"b"));
}
