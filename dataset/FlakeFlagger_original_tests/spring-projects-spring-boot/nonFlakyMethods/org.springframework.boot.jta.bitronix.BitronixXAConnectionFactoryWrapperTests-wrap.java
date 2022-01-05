@Test public void wrap(){
  XAConnectionFactory connectionFactory=mock(XAConnectionFactory.class);
  BitronixXAConnectionFactoryWrapper wrapper=new BitronixXAConnectionFactoryWrapper();
  ConnectionFactory wrapped=wrapper.wrapConnectionFactory(connectionFactory);
  assertThat(wrapped,instanceOf(PoolingConnectionFactoryBean.class));
  assertThat(((PoolingConnectionFactoryBean)wrapped).getConnectionFactory(),sameInstance(connectionFactory));
}
