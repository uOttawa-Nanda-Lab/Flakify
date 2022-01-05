@Test public void createWithDetails() throws Exception {
  Health health=new Health.Builder(Status.UP,Collections.singletonMap("a","b")).build();
  assertThat(health.getStatus(),equalTo(Status.UP));
  assertThat(health.getDetails().get("a"),equalTo((Object)"b"));
}
