@Test public void testSerialization() throws Exception {
  Map<String,HealthIndicator> indicators=new HashMap<String,HealthIndicator>();
  indicators.put("db1",this.one);
  indicators.put("db2",this.two);
  CompositeHealthIndicator innerComposite=new CompositeHealthIndicator(this.healthAggregator,indicators);
  CompositeHealthIndicator composite=new CompositeHealthIndicator(this.healthAggregator);
  composite.addHealthIndicator("db",innerComposite);
  Health result=composite.health();
  ObjectMapper mapper=new ObjectMapper();
  assertEquals("{\"status\":\"UNKNOWN\",\"db\":{\"status\":\"UNKNOWN\"" + ",\"db1\":{\"status\":\"UNKNOWN\",\"1\":\"1\"}," + "\"db2\":{\"status\":\"UNKNOWN\",\"2\":\"2\"}}}",mapper.writeValueAsString(result));
}
