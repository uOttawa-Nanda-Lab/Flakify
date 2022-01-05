@Test public void testMask0(){
  testMaskAcrossDimensions(0,new MaskCondition(){
    @Override public boolean isMasked(    int i,    int j){
      return (i + j) % 2 == 0;
    }
  }
);
}
