@Test(expected=IllegalArgumentException.class) public void should_throw_exception_if_tombstone_threshold_out_of_range() throws Exception {
  TableOptions.CompactionOptions.sizedTieredStategy().bucketLow(0.5).bucketHigh(1.2).tombstoneThreshold(1.89).build();
}
