@Test public void withDetails() throws Exception {
  Health health=new Health.Builder(Status.UP,Collections.singletonMap("a","b")).withDetail("c","d").build();
  assertThat(health.getDetails().get("a"),equalTo((Object)"b"));
  assertThat(health.getDetails().get("c"),equalTo((Object)"d"));
}
