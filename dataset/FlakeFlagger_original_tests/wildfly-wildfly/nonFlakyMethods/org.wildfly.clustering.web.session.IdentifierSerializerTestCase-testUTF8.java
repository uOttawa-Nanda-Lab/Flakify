@Test public void testUTF8() throws IOException {
  test(IdentifierSerializer.UTF8,() -> UUID.randomUUID().toString());
}
