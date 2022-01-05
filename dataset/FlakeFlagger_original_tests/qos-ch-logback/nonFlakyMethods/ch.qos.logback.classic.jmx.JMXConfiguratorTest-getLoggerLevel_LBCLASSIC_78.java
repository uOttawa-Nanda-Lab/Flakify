@Test public void getLoggerLevel_LBCLASSIC_78(){
  String objectNameAsStr="ch.qos" + diff + ":Name="+ lc.getName()+ ",Type="+ this.getClass().getName();
  ObjectName on=MBeanUtil.string2ObjectName(lc,this,objectNameAsStr);
  JMXConfigurator configurator=new JMXConfigurator(lc,mbs,on);
  assertEquals("",configurator.getLoggerLevel(testLogger.getName()));
  MBeanUtil.unregister(lc,mbs,on,this);
}
