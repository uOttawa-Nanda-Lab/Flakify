@Test public void createWithStatus() throws Exception {
  Health health=Health.status(Status.UP).build();
  assertThat(health.getStatus(),equalTo(Status.UP));
  assertThat(health.getDetails().size(),equalTo(0));
}
