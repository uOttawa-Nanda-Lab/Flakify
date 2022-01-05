@Test public void capacityLimited(){
  this.repository.setCapacity(2);
  this.repository.add(Collections.<String,Object>singletonMap("foo","bar"));
  this.repository.add(Collections.<String,Object>singletonMap("bar","foo"));
  this.repository.add(Collections.<String,Object>singletonMap("bar","bar"));
  List<Trace> traces=this.repository.findAll();
  assertEquals(2,traces.size());
  assertEquals("bar",traces.get(1).getInfo().get("bar"));
}
