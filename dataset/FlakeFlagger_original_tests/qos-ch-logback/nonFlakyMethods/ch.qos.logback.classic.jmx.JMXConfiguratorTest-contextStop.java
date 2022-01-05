@Test public void contextStop() throws Exception {
  String randomizedObjectNameAsStr="ch.qos.logback." + diff + ":Name="+ lc.getName()+ ",Type="+ this.getClass().getName();
  ObjectName objectName=MBeanUtil.string2ObjectName(lc,this,randomizedObjectNameAsStr);
  JMXConfigurator jmxConfigurator=new JMXConfigurator(lc,mbs,objectName);
  mbs.registerMBean(jmxConfigurator,objectName);
  listenerList=lc.getCopyOfListenerList();
  assertEquals(1,listenerList.size());
  lc.stop();
  listenerList=lc.getCopyOfListenerList();
  assertEquals(0,listenerList.size());
  assertFalse(mbs.isRegistered(objectName));
}
