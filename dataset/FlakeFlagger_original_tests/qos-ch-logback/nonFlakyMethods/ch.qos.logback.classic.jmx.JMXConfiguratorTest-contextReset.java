@Test public void contextReset() throws Exception {
  String randomizedObjectNameAsStr="ch.qos.logback." + diff + ":Name="+ lc.getName()+ ",Type="+ this.getClass().getName();
  ObjectName objectName=MBeanUtil.string2ObjectName(lc,this,randomizedObjectNameAsStr);
  JMXConfigurator jmxConfigurator=new JMXConfigurator(lc,mbs,objectName);
  mbs.registerMBean(jmxConfigurator,objectName);
  listenerList=lc.getCopyOfListenerList();
  assertEquals(1,listenerList.size());
  lc.reset();
  listenerList=lc.getCopyOfListenerList();
  assertEquals(1,listenerList.size());
  assertTrue(listenerList.contains(jmxConfigurator));
  assertTrue(mbs.isRegistered(objectName));
}
