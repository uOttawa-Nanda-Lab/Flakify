@Test public void closeNonClosableContext(){
  ApplicationContext mock=mock(ApplicationContext.class);
  ApplicationContextTestUtils.closeAll(mock);
}
