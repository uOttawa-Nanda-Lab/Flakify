@Test public void should_build_compressions_options_for_lz4() throws Exception {
  final String build=TableOptions.CompressionOptions.lz4().withChunkLengthInKb(128).withCRCCheckChance(0.6D).build();
  assertThat(build).isEqualTo("{'sstable_compression' : 'LZ4Compressor', 'chunk_length_kb' : 128, 'crc_check_chance' : 0.6}");
}
