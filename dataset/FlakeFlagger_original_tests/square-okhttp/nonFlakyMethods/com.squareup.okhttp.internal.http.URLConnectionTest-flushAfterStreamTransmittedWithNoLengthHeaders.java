@Test public void flushAfterStreamTransmittedWithNoLengthHeaders() throws IOException {
  testFlushAfterStreamTransmitted(TransferKind.END_OF_STREAM);
}
