@Test public void findHost(){
  org.jboss.modcluster.container.Host result=this.engine.findHost(this.hostName);
  assertSame(this.hostName,result.getName());
  assertSame(this.engine,result.getEngine());
  assertNull(this.engine.findHost("no-such-host"));
}
