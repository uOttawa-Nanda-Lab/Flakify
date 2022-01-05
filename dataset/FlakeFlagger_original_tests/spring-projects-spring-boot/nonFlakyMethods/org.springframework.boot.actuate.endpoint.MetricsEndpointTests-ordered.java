@Test public void ordered(){
  List<PublicMetrics> publicMetrics=new ArrayList<PublicMetrics>();
  publicMetrics.add(new TestPublicMetrics(2,this.metric2,this.metric2,this.metric3));
  publicMetrics.add(new TestPublicMetrics(1,this.metric1));
  Map<String,Object> metrics=new MetricsEndpoint(publicMetrics).invoke();
  Iterator<Entry<String,Object>> iterator=metrics.entrySet().iterator();
  assertEquals("a",iterator.next().getKey());
  assertEquals("b",iterator.next().getKey());
  assertEquals("c",iterator.next().getKey());
  assertFalse(iterator.hasNext());
}
