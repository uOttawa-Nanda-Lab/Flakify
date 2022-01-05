@Test(expected=IllegalStateException.class) public void shouldFailWhenConfigurationFactoryFails() throws Exception {
  victim=new WroFilter(){
    @Override protected ObjectFactory<WroConfiguration> newWroConfigurationFactory(    final FilterConfig filterConfig){
      throw new IllegalStateException("BOOM!");
    }
  }
;
  victim.init(mockFilterConfig);
}
