@Test public void should_set_bounding_mode() throws Exception {
  builder.partitionComponentsInternal(10L).bounding(EXCLUSIVE_BOUNDS);
  assertThat(builder.buildClusterQuery().getBounding()).isEqualTo(EXCLUSIVE_BOUNDS);
}
