@Test public void should_create_snappy_compressions_options() throws Exception {
  final String build=TableOptions.CompressionOptions.snappy().withChunkLengthInKb(128).withCRCCheckChance(0.6D).build();
  assertThat(build).isEqualTo("{'sstable_compression' : 'SnappyCompressor', 'chunk_length_kb' : 128, 'crc_check_chance' : 0.6}");
}
