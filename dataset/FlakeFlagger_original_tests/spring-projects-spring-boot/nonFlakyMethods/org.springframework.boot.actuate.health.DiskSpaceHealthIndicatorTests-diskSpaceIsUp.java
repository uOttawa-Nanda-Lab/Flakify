@Test public void diskSpaceIsUp() throws Exception {
  given(this.fileMock.getFreeSpace()).willReturn(THRESHOLD_BYTES + 10);
  Health health=this.healthIndicator.health();
  assertEquals(Status.UP,health.getStatus());
  assertEquals(THRESHOLD_BYTES,health.getDetails().get("threshold"));
  assertEquals(THRESHOLD_BYTES + 10,health.getDetails().get("free"));
}
