public void testColumnPaginationFilter() throws Exception {
  KeyValue[] expectedKVs={new KeyValue(ROWS_ONE[0],FAMILIES[0],QUALIFIERS_ONE[0],VALUES[0]),new KeyValue(ROWS_ONE[2],FAMILIES[0],QUALIFIERS_ONE[0],VALUES[0]),new KeyValue(ROWS_ONE[3],FAMILIES[0],QUALIFIERS_ONE[0],VALUES[0]),new KeyValue(ROWS_TWO[0],FAMILIES[0],QUALIFIERS_TWO[0],VALUES[1]),new KeyValue(ROWS_TWO[2],FAMILIES[0],QUALIFIERS_TWO[0],VALUES[1]),new KeyValue(ROWS_TWO[3],FAMILIES[0],QUALIFIERS_TWO[0],VALUES[1])};
  KeyValue[] expectedKVs2={new KeyValue(ROWS_ONE[0],FAMILIES[0],QUALIFIERS_ONE[3],VALUES[0]),new KeyValue(ROWS_ONE[2],FAMILIES[0],QUALIFIERS_ONE[3],VALUES[0]),new KeyValue(ROWS_ONE[3],FAMILIES[0],QUALIFIERS_ONE[3],VALUES[0]),new KeyValue(ROWS_TWO[0],FAMILIES[0],QUALIFIERS_TWO[3],VALUES[1]),new KeyValue(ROWS_TWO[2],FAMILIES[0],QUALIFIERS_TWO[3],VALUES[1]),new KeyValue(ROWS_TWO[3],FAMILIES[0],QUALIFIERS_TWO[3],VALUES[1])};
  KeyValue[] expectedKVs3={new KeyValue(ROWS_ONE[0],FAMILIES[0],QUALIFIERS_ONE[3],VALUES[0]),new KeyValue(ROWS_ONE[0],FAMILIES[1],QUALIFIERS_ONE[0],VALUES[0]),new KeyValue(ROWS_ONE[2],FAMILIES[0],QUALIFIERS_ONE[3],VALUES[0]),new KeyValue(ROWS_ONE[2],FAMILIES[1],QUALIFIERS_ONE[0],VALUES[0]),new KeyValue(ROWS_ONE[3],FAMILIES[0],QUALIFIERS_ONE[3],VALUES[0]),new KeyValue(ROWS_ONE[3],FAMILIES[1],QUALIFIERS_ONE[0],VALUES[0]),new KeyValue(ROWS_TWO[0],FAMILIES[0],QUALIFIERS_TWO[3],VALUES[1]),new KeyValue(ROWS_TWO[0],FAMILIES[1],QUALIFIERS_TWO[0],VALUES[1]),new KeyValue(ROWS_TWO[2],FAMILIES[0],QUALIFIERS_TWO[3],VALUES[1]),new KeyValue(ROWS_TWO[2],FAMILIES[1],QUALIFIERS_TWO[0],VALUES[1]),new KeyValue(ROWS_TWO[3],FAMILIES[0],QUALIFIERS_TWO[3],VALUES[1]),new KeyValue(ROWS_TWO[3],FAMILIES[1],QUALIFIERS_TWO[0],VALUES[1])};
  KeyValue[] expectedKVs4={};
  long expectedRows=this.numRows;
  long expectedKeys=1;
  Scan s=new Scan();
  s.setFilter(new ColumnPaginationFilter(1,0));
  verifyScan(s,expectedRows,expectedKeys);
  this.verifyScanFull(s,expectedKVs);
  s.setFilter(new ColumnPaginationFilter(1,2));
  verifyScan(s,expectedRows,expectedKeys);
  this.verifyScanFull(s,expectedKVs2);
  s.setFilter(new ColumnPaginationFilter(2,2));
  expectedKeys=2;
  verifyScan(s,expectedRows,expectedKeys);
  this.verifyScanFull(s,expectedKVs3);
  s.setFilter(new ColumnPaginationFilter(20,140));
  expectedKeys=0;
  expectedRows=0;
  verifyScan(s,expectedRows,0);
  this.verifyScanFull(s,expectedKVs4);
}
