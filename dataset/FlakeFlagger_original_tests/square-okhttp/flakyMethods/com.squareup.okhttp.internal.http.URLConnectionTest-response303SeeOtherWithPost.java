@Test public void response303SeeOtherWithPost() throws Exception {
  testResponseRedirectedWithPost(HttpURLConnection.HTTP_SEE_OTHER);
}
