@Test public void should_create_leveled_compaction_option() throws Exception {
  final String build=TableOptions.CompactionOptions.leveledStrategy().ssTableSizeInMB(160).enableAutoCompaction(true).maxThreshold(5).tombstoneCompactionIntervalInDay(3).tombstoneThreshold(0.7).build();
  assertThat(build).isEqualTo("{'class' : 'LeveledCompactionStrategy', 'enabled' : true, 'max_threshold' : 5, 'tombstone_compaction_interval' : 3, 'tombstone_threshold' : 0.7, 'sstable_size_in_mb' : 160}");
}
