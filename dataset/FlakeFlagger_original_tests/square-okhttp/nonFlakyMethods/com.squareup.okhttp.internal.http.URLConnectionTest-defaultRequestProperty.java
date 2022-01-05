@SuppressWarnings("deprecation") @Test public void defaultRequestProperty() throws Exception {
  URLConnection.setDefaultRequestProperty("X-testSetDefaultRequestProperty","A");
  assertNull(URLConnection.getDefaultRequestProperty("X-setDefaultRequestProperty"));
}
