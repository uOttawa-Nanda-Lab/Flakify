@Test public void requestBodySurvivesRetriesWithBufferedBody() throws Exception {
  testRequestBodySurvivesRetries(TransferKind.END_OF_STREAM);
}
