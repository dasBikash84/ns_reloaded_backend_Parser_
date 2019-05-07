/*
 * Copyright 2019 das.bikash.dev@gmail.com. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dasbikash.news_server_parser.parser.article_body_parsers.daily_sun;

import com.dasbikash.news_server_parser.parser.article_body_parsers.ArticleBodyParser;
import org.jsoup.select.Elements;

public class DailySunArticleParser extends ArticleBodyParser {

    private static final String TAG = "StackTrace";

    private final String mSiteBaseAddress = "https://www.daily-sun.com";

    @Override
    protected String getSiteBaseAddress() {
        return mSiteBaseAddress;
    }

    @Override
    protected String getArticleModificationDateString() {
        Elements h3Blocks = mDocument.select(DailySunArticleParserInfo.ARTICLE_MODIFICATION_DATE_STRING_SELECTOR);

        System.out.println("h3Blocks.size(): " + h3Blocks.size());
        if (h3Blocks.size()>= 4){
            String fullDateString = h3Blocks.get(3).text();
            System.out.println("fullDateString: " + fullDateString);
//            String dateString = fullDateString.replaceFirst(
//                    DailySunArticleParserInfo.ARTICLE_MODIFICATION_DATE_STRING_REPLACEMENT_SELECTOR,
//                    DailySunArticleParserInfo.ARTICLE_MODIFICATION_DATE_STRING_REPLACEMENT_STRING
//            ).trim();
            return fullDateString;
//            return dateString;
        }
        return null;
    }

    @Override
    protected String[] getArticleModificationDateStringFormats() {
        return DailySunArticleParserInfo.ARTICLE_MODIFICATION_DATE_STRING_FORMATS;
    }

    @Override
    protected String getFeaturedImageSelector() {
        return DailySunArticleParserInfo.FEATURED_IMAGE_SELECTOR;
    }

    @Override
    protected String getFeaturedImageLinkSelectorAttr() {
        return DailySunArticleParserInfo.FEATURED_IMAGE_LINK_SELECTOR_ATTR;
    }

    @Override
    protected String getFeaturedImageCaptionSelectorAttr() {
        return DailySunArticleParserInfo.FEATURED_IMAGE_CAPTION_SELECTOR_ATTR;
    }

    @Override
    protected String getFeaturedImageCaptionSelector() {
        return null;
    }


    @Override
    protected Elements getArticleFragmentBlocks() {
        Elements articleFragmentBlocks = mDocument.select(DailySunArticleParserInfo.ARTICLE_FRAGMENT_BLOCK_SELECTOR);
        return articleFragmentBlocks;
    }

    @Override
    protected String getParagraphImageSelector() {
        return DailySunArticleParserInfo.PARAGRAPH_IMAGE_SELECTOR;
    }
    @Override
    protected String getParagraphImageLinkSelectorAttr() {
        return DailySunArticleParserInfo.PARAGRAPH_IMAGE_LINK_SELECTOR_ATTR;
    }

    @Override
    protected String getParagraphImageCaptionSelector() {
        return null;
    }

    @Override
    protected String getParagraphImageCaptionSelectorAttr() {
        return DailySunArticleParserInfo.PARAGRAPH_IMAGE_CAPTION_SELECTOR_ATTR;
    }
}
