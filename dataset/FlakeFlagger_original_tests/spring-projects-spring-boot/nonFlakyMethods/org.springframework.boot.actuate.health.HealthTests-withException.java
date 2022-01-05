@Test public void withException() throws Exception {
  RuntimeException ex=new RuntimeException("bang");
  Health health=new Health.Builder(Status.UP,Collections.singletonMap("a","b")).withException(ex).build();
  assertThat(health.getDetails().get("a"),equalTo((Object)"b"));
  assertThat(health.getDetails().get("error"),equalTo((Object)"java.lang.RuntimeException: bang"));
}
