@Test public void wrap(){
  XAConnectionFactory connectionFactory=mock(XAConnectionFactory.class);
  AtomikosXAConnectionFactoryWrapper wrapper=new AtomikosXAConnectionFactoryWrapper();
  ConnectionFactory wrapped=wrapper.wrapConnectionFactory(connectionFactory);
  assertThat(wrapped,instanceOf(AtomikosConnectionFactoryBean.class));
  assertThat(((AtomikosConnectionFactoryBean)wrapped).getXaConnectionFactory(),sameInstance(connectionFactory));
}
