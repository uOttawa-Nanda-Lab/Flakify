@Test(expected=IllegalArgumentException.class) public void should_throw_exception_if_cold_read_ration_negative() throws Exception {
  TableOptions.CompactionOptions.sizedTieredStategy().bucketLow(0.5).bucketHigh(1.2).coldReadsRatioToOmit(-1.0).build();
}
