@Test(expected=IllegalArgumentException.class) public void testTaggedNotPairs(){
  MetricName.EMPTY.tag("foo");
}
