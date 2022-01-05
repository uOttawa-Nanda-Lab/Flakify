@Test public void testEqualsAndHashCodeSpanningSegments() throws Exception {
  byte[] data=new byte[1024 * 1024];
  Random dice=new Random(0);
  dice.nextBytes(data);
  OkBuffer a=bufferWithRandomSegmentLayout(dice,data);
  OkBuffer b=bufferWithRandomSegmentLayout(dice,data);
  assertTrue(a.equals(b));
  assertTrue(a.hashCode() == b.hashCode());
  data[data.length / 2]++;
  OkBuffer c=bufferWithRandomSegmentLayout(dice,data);
  assertFalse(a.equals(c));
  assertFalse(a.hashCode() == c.hashCode());
}
