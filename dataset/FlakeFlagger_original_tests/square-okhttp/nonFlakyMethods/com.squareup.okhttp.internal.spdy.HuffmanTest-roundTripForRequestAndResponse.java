@Test public void roundTripForRequestAndResponse() throws IOException {
  String s="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  for (int i=0; i < s.length(); i++) {
    assertRoundTrip(s.substring(0,i).getBytes());
  }
  Random random=new Random(123456789L);
  byte[] buf=new byte[4096];
  random.nextBytes(buf);
  assertRoundTrip(buf);
}
