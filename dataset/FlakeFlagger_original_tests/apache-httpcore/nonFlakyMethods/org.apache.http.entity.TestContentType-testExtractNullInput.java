@Test public void testExtractNullInput() throws Exception {
  Assert.assertNull(ContentType.get(null));
}
