@Test public void testExtends(){
  PongFrame frame=new PongFrame();
  assertEquals("Frame must extend dataframe",true,frame instanceof ControlFrame);
}
