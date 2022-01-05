@Test public void should_create_no_compressions_options() throws Exception {
  final String build=TableOptions.CompressionOptions.none().withChunkLengthInKb(128).withCRCCheckChance(0.6D).build();
  assertThat(build).isEqualTo("{'sstable_compression' : ''}");
}
