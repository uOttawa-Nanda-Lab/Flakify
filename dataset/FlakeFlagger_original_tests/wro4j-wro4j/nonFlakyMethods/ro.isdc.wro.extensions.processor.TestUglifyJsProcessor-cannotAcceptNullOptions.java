@Test(expected=NullPointerException.class) public void cannotAcceptNullOptions() throws Exception {
  new UglifyJs(UGLIFY){
    protected String createOptionsAsJson() throws IOException {
      return null;
    }
  }
.process("filename","alert(1);");
}
