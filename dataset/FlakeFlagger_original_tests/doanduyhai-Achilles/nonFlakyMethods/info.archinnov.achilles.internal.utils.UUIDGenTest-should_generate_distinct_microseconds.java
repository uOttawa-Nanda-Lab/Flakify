@Test public void should_generate_distinct_microseconds() throws Exception {
  Long timestampInMicros=UUIDGen.increasingMicroTimestamp();
  for (int i=0; i < 1000; i++) {
    Long newTimestampInMicros=UUIDGen.increasingMicroTimestamp();
    assertThat(newTimestampInMicros).isGreaterThan(timestampInMicros);
    timestampInMicros=newTimestampInMicros;
  }
}
