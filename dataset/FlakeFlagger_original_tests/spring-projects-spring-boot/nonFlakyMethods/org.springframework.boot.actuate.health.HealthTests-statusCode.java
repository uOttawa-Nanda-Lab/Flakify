@Test public void statusCode() throws Exception {
  Health health=Health.status("UP").build();
  assertThat(health.getStatus(),equalTo(Status.UP));
  assertThat(health.getDetails().size(),equalTo(0));
}
