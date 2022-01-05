@Test public void testMask5(){
  testMaskAcrossDimensions(5,new MaskCondition(){
    @Override public boolean isMasked(    int i,    int j){
      return (i * j) % 2 + (i * j) % 3 == 0;
    }
  }
);
}
