@Test public void falsyHash() throws IOException {
  Hash helpers=$("helper",new Helper<Object>(){
    @Override public CharSequence apply(    final Object context,    final Options options) throws IOException {
      assertEquals(false,options.hash("b"));
      return "ok";
    }
  }
);
  shouldCompileTo("{{helper . b=false}}",new Object(),helpers,"ok");
}
