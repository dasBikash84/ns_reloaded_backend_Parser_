package com.dasbikash.news_server_parser.parser.article_body_parsers.the_daily_star

import com.dasbikash.news_server_parser.model.EntityClassNames
import com.dasbikash.news_server_parser.model.Newspaper
import com.dasbikash.news_server_parser.parser.NEWS_PAPER_ID
import com.dasbikash.news_server_parser.parser.article_body_parsers.ArticleBodyParser
import com.dasbikash.news_server_parser.parser.preview_page_parsers.PreviewPageParser
import com.dasbikash.news_server_parser.utils.DbSessionManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class TheDailyStarArticleParserTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun readFirstPageArticles(){

        val hql = "FROM ${EntityClassNames.NEWSPAPER} where active=true"
        val  session = DbSessionManager.getNewSession()
        val query = session.createQuery(hql)
        val newsPapers= query.list() as List<Newspaper>

        //session.close()

        newsPapers.filter {
            it.id == NEWS_PAPER_ID.THE_DAILY_STAR.id
        }.map {
            println("Np: ${it?.name}")
            println("Page: ${it.pageList?.get(0)?.name}")
            it.pageList?.filter {
                it.linkFormat !=null
            }?.first()
        }.map {
            it?.let {
                val articleList = PreviewPageParser.parsePreviewPageForArticles(it,1)
                it.articleList = articleList
                it.articleList
                        ?.forEach {
                            //session.persist(it)
                            println("Article  ${it}")
                        }
            }
            it
        }.map {
            it?.let {
                println("Article Data before parsing:"+it.articleList?.first())
                println("Article Data after parsing:"+ ArticleBodyParser.getArticleBody(it.articleList?.get(Random(it.articleList?.size!!).nextInt(it.articleList?.size!!))))
//                println(ArticleBodyParser.getArticleBody(it.articleList?.first()))
            }
        }

    }
}