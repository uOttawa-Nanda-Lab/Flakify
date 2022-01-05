@Test public void response301MovedPermanentlyWithPost() throws Exception {
  testResponseRedirectedWithPost(HttpURLConnection.HTTP_MOVED_PERM);
}
