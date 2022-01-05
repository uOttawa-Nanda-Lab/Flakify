@Test public void inputStreamAvailableWithChunkedEncoding() throws Exception {
  testInputStreamAvailable(TransferKind.CHUNKED);
}
