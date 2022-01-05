@Test public void response302MovedTemporarilyWithPost() throws Exception {
  testResponseRedirectedWithPost(HttpURLConnection.HTTP_MOVED_TEMP);
}
