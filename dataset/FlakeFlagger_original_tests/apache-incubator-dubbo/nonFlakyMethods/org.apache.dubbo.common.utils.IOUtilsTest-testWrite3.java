@Test public void testWrite3() throws Exception {
  assertThat((int)IOUtils.write(writer,TEXT),equalTo(TEXT.length()));
}
