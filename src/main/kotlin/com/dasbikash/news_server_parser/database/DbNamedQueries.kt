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

package com.dasbikash.news_server_parser.database

object DbNamedNativeQueries {
    const val UN_PARSERD_ARTICLES_BY_NEWSPAPER_ID_NAME = "Article.getUnParserdArticlesByNewsPaperId"
    const val UN_PARSERD_ARTICLES_BY_NEWSPAPER_ID_QUERY =
            "select articles.id,articles.pageId,articles.title,articles.modificationTS,articles.publicationTS," +
            "articles.modified,articles.articleText,articles.previewImageLink,articles.articleLink" +
            " from articles inner join pages on pages.id=articles.pageId " +
            " inner join newspapers on newspapers.id=pages.newsPaperId " +
            " where articles.articleText is null " +
            " and newspapers.id = :currentNewsPaperId"
}