/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;

import ninja.utils.HttpCacheToolkit;
import ninja.utils.MimeTypes;
import ninja.utils.NinjaProperties;
import ninja.utils.ResponseStreams;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssetsControllerTest {

    @Mock
    MimeTypes mimeTypes;

    @Mock
    HttpCacheToolkit httpCacheToolkit;

    @Mock
    Context contextRenerable;

    @Captor
    ArgumentCaptor<Result> resultCaptor;

    @Mock
    ResponseStreams responseStreams;
    
    @Mock 
    NinjaProperties ninjaProperties;

    @Test public void testAssetsController304NotModified() throws Exception{AssetsController assetsController=new AssetsController(httpCacheToolkit,mimeTypes,ninjaProperties);when(contextRenerable.getRequestPath()).thenReturn("/assets/testasset.txt");Result result2=assetsController.serve(null);Renderable renderable=(Renderable)result2.getRenderable();Result result=Results.ok();result.status(Result.SC_304_NOT_MODIFIED);renderable.render(contextRenerable,result);verify(httpCacheToolkit).addEtag(Mockito.eq(contextRenerable),Mockito.eq(result),Mockito.anyLong());verify(contextRenerable).finalizeHeadersWithoutFlashAndSessionCookie(resultCaptor.capture());assertEquals(Result.SC_304_NOT_MODIFIED,resultCaptor.getValue().getStatusCode());}

}
