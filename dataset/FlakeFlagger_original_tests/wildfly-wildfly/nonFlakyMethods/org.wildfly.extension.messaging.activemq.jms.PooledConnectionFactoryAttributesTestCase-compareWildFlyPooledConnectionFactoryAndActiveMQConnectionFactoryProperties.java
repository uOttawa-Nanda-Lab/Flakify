@Test public void compareWildFlyPooledConnectionFactoryAndActiveMQConnectionFactoryProperties() throws Exception {
  SortedSet<String> pooledConnectionFactoryAttributes=findAllResourceAdapterProperties(PooledConnectionFactoryDefinition.ATTRIBUTES);
  pooledConnectionFactoryAttributes.removeAll(KNOWN_ATTRIBUTES);
  SortedSet<String> activemqRAProperties=findAllPropertyNames(ActiveMQResourceAdapter.class);
  activemqRAProperties.removeAll(UNSUPPORTED_ACTIVEMQ_RA_PROPERTIES);
  compare("AS7 PooledConnectionFactoryAttributes",pooledConnectionFactoryAttributes,"ActiveMQ Resource Adapter",activemqRAProperties);
}
