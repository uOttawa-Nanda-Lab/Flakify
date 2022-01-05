@Test public void should_get_type() throws Exception {
  assertThat(context.type()).isSameAs(FlushType.BATCH);
}
