@Test public void diskSpaceIsDown() throws Exception {
  given(this.fileMock.getFreeSpace()).willReturn(THRESHOLD_BYTES - 10);
  Health health=this.healthIndicator.health();
  assertEquals(Status.DOWN,health.getStatus());
  assertEquals(THRESHOLD_BYTES,health.getDetails().get("threshold"));
  assertEquals(THRESHOLD_BYTES - 10,health.getDetails().get("free"));
}
