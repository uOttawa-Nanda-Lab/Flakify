@Test public void authenticateWithChunkedStreaming() throws Exception {
  testAuthenticateWithStreamingPost(StreamingMode.CHUNKED);
}
