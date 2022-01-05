@Test public void testMask3(){
  testMaskAcrossDimensions(3,new MaskCondition(){
    @Override public boolean isMasked(    int i,    int j){
      return (i + j) % 3 == 0;
    }
  }
);
}
