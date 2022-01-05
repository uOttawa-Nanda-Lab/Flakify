@Test public void status() throws Exception {
  Health health=Health.status(Status.UP).build();
  assertThat(health.getStatus(),equalTo(Status.UP));
  assertThat(health.getDetails().size(),equalTo(0));
}
