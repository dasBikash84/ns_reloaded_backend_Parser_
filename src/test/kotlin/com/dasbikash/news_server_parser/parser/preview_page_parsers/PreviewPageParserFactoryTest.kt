package com.dasbikash.news_server_parser.parser.preview_page_parsers

import com.dasbikash.news_server_parser.database.DbSessionManager
import com.dasbikash.news_server_parser.model.EntityClassNames
import com.dasbikash.news_server_parser.model.Newspaper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PreviewPageParserFactoryTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun readFirstPageArticles(){

        val hql = "FROM ${EntityClassNames.NEWSPAPER} where active=true"
        val query = DbSessionManager.getNewSession().createQuery(hql,Newspaper::class.java)
        val newsPapers= query.list() as List<Newspaper>

        newsPapers.filter {
            true
        }.map {
            println("Np: ${it.name}")
            println("Page: ${it.pageList?.get(0)?.name}")
            it.pageList?.filter {
                it.linkFormat !=null
            }?.get(3)
        }.map {
            it?.let {
                PreviewPageParser.parsePreviewPageForArticles(it,1)
                        ?.forEach {
                            println("Article  ${it}")
                        }
                Thread.sleep(500)
            }
        }

    }
}