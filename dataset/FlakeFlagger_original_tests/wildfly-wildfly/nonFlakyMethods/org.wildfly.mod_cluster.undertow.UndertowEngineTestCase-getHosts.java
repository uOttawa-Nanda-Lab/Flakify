@Test public void getHosts(){
  Iterator<org.jboss.modcluster.container.Host> results=this.engine.getHosts().iterator();
  assertTrue(results.hasNext());
  org.jboss.modcluster.container.Host host=results.next();
  assertSame(this.hostName,host.getName());
  assertSame(this.engine,host.getEngine());
  assertFalse(results.hasNext());
}
