@Test public void testRead() throws Exception {
  assertThat(IOUtils.read(reader),equalTo(TEXT));
}
