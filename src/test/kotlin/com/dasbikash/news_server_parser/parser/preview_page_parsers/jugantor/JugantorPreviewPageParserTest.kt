package com.dasbikash.news_server_parser.parser.preview_page_parsers.jugantor

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

internal class JugantorPreviewPageParserTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    /*@Test
    fun readFirstPageArticles() {

        val hql = "FROM ${EntityClassNames.NEWSPAPER} where active=true"
        val query = DbSessionManager.getNewSession().createQuery(hql,Newspaper::class.java)
        val newsPapers = query.list() as List<Newspaper>

        newsPapers.filter {
            it.id == NEWS_PAPER_ID.JUGANTOR.id
        }.map {
            println("Np: ${it.name}")
            println("Page: ${it.pageList?.get(0)?.name}")
            it.pageList?.filter {
                it.hasData()
            }?.get(10)
        }.map {
            it?.let {
                PreviewPageParser.parsePreviewPageForArticles(it, 1).first
                        ?.forEach {
                            println("Article  ${it}")
                        }
            }
        }

    }*/
}