@Test public void should_create_sized_tiered_compaction_options() throws Exception {
  final String build=TableOptions.CompactionOptions.sizedTieredStategy().bucketLow(0.5).bucketHigh(1.2).coldReadsRatioToOmit(0.89).minThreshold(2).maxThreshold(4).minSSTableSizeInBytes(5000000L).enableAutoCompaction(true).tombstoneCompactionIntervalInDay(3).tombstoneThreshold(0.7).build();
  assertThat(build).isEqualTo("{'class' : 'SizeTieredCompactionStrategy', 'enabled' : true, 'max_threshold' : 4, 'tombstone_compaction_interval' : 3, 'tombstone_threshold' : 0.7, 'bucket_high' : 1.2, 'bucket_low' : 0.5, 'cold_reads_to_omit' : 0.89, 'min_threshold' : 2, 'min_sstable_size' : 5000000}");
}
