@Test public void testNonRemovalOfPreviousIntanceFromTheContextListenerList(){
  String objectNameAsStr="ch.qos.logback.toto" + ":Name=" + lc.getName() + ",Type="+ this.getClass().getName();
  ObjectName objectName=MBeanUtil.string2ObjectName(lc,this,objectNameAsStr);
  JMXConfigurator jmxConfigurator0=new JMXConfigurator(lc,mbs,objectName);
  listenerList=lc.getCopyOfListenerList();
  assertTrue(listenerList.contains(jmxConfigurator0));
  JMXConfigurator jmxConfigurator1=new JMXConfigurator(lc,mbs,objectName);
  listenerList=lc.getCopyOfListenerList();
  assertEquals(1,listenerList.size());
  assertTrue("old configurator should be present",listenerList.contains(jmxConfigurator0));
  assertFalse("new configurator should be absent",listenerList.contains(jmxConfigurator1));
}
