package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue123 extends AbstractTest {

  @Test public void spacesInBlock() throws IOException{shouldCompileTo("{{#if \"stuff\" }}Bingo{{/if}}",$,"Bingo");shouldCompileTo("{{#if \"stuff\"  }}Bingo{{/if}}",$,"Bingo");shouldCompileTo("{{#if \"stuff\"}}Bingo{{/if}}",$,"Bingo");shouldCompileTo("{{# if \"stuff\"}}Bingo{{/if}}",$,"Bingo");shouldCompileTo("{{#if \"stuff\"}}Bingo{{/ if}}",$,"Bingo");shouldCompileTo("{{# if \"stuff\" }}Bingo{{/ if }}",$,"Bingo");}
}
