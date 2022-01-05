@Test public void testMBean() throws Exception {
  final JMXConnector connector=JMXConnectorFactory.connect(managementClient.getRemoteJMXURL());
  try {
    MBeanServerConnection mbeanServer=connector.getMBeanServerConnection();
    ObjectName objectName=new ObjectName("jboss:name=test,type=config");
    mbeanServer.getAttribute(objectName,"IntervalSeconds");
    mbeanServer.setAttribute(objectName,new Attribute("IntervalSeconds",2));
  }
  finally {
    IoUtils.safeClose(connector);
  }
}
