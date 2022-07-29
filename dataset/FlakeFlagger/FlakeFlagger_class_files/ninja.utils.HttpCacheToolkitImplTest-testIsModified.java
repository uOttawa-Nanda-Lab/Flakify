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

package ninja.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ninja.Context;
import ninja.Result;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;

@RunWith(MockitoJUnitRunner.class)
public class HttpCacheToolkitImplTest {

    @Mock
    NinjaProperties ninjaProperties;

    @Mock
    Result result;

    @Mock
    Context context;

    @Test public void testIsModified(){HttpCacheToolkit httpCacheToolkit=new HttpCacheToolkitImpl(ninjaProperties);when(context.getHeader(HttpHeaderConstants.IF_NONE_MATCH)).thenReturn("etag_xyz");assertFalse(httpCacheToolkit.isModified(Optional.of("etag_xyz"),Optional.of(0L),context));assertTrue(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));when(context.getHeader(HttpHeaderConstants.IF_NONE_MATCH)).thenReturn(null);when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn(null);assertTrue(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn("Thu, 01 Jan 1970 00:00:00 GMT");assertTrue(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(1000L),context));when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn("Thu, 01 Jan 1970 00:00:00 GMT");assertFalse(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn("Thu, 01 Jan 1971 00:00:00 GMT");assertFalse(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));when(context.getHeader(HttpHeaderConstants.IF_MODIFIED_SINCE)).thenReturn("STRANGE_TIMESTAMP");assertTrue(httpCacheToolkit.isModified(Optional.of("etag_xyz_modified"),Optional.of(0L),context));}

}
