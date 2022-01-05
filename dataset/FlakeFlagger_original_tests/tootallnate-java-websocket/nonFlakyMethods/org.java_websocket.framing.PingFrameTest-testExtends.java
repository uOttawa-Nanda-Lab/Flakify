@Test public void testExtends(){
  PingFrame frame=new PingFrame();
  assertEquals("Frame must extend dataframe",true,frame instanceof ControlFrame);
}
