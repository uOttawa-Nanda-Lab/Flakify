@Test public void should_return_IMMEDIATE_type() throws Exception {
  assertThat(context.type()).isSameAs(FlushType.IMMEDIATE);
}
