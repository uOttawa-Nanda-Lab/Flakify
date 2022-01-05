@Test public void setLoggerLevel_LBCLASSIC_79(){
  String objectNameAsStr="ch.qos" + diff + ":Name="+ lc.getName()+ ",Type="+ this.getClass().getName();
  ObjectName on=MBeanUtil.string2ObjectName(lc,this,objectNameAsStr);
  JMXConfigurator configurator=new JMXConfigurator(lc,mbs,on);
  configurator.setLoggerLevel(testLogger.getName(),"DEBUG");
  assertEquals(Level.DEBUG,testLogger.getLevel());
  configurator.setLoggerLevel(testLogger.getName(),"null");
  assertNull(testLogger.getLevel());
  MBeanUtil.unregister(lc,mbs,on,this);
}
