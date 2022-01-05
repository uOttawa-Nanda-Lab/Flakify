@Test(expected=IllegalArgumentException.class) public void testTaggedNotPairs2(){
  MetricName.EMPTY.tag("foo","bar","baz");
}
