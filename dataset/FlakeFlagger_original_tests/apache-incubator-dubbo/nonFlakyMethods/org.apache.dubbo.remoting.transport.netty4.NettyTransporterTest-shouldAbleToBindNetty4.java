@Test public void shouldAbleToBindNetty4() throws Exception {
  int port=NetUtils.getAvailablePort();
  URL url=new URL("http","localhost",port,new String[]{Constants.BIND_PORT_KEY,String.valueOf(port)});
  Server server=new NettyTransporter().bind(url,new ChannelHandlerAdapter());
  assertThat(server.isBound(),is(true));
}
