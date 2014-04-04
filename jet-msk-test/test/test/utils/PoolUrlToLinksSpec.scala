package test.utils

import scala.collection.Seq
import org.specs2.specification.Fragments
import org.specs2.main.ArgProperty
import models.PoolUrlToLinks
import scala.xml.XML
import org.specs2.mutable._

class PoolUrlToLinksSpec extends Specification {
  val fillXml = """<?xml version="1.0" encoding="utf-8"?>
<rss xmlns:yablogs="urn:yandex-blogs" xmlns:wfw="http://wellformedweb.org/CommentAPI/" version="2.0">
  <channel>
    <link>http://blogs.yandex.ru/search.xml?text=scala</link>
    <item>
      <author>http://jekins.ru/news/</author>
      <yablogs:author url="http://jekins.ru/news/">jekins.ru/news/</yablogs:author>
      <title>Club of fans Dance Vol.2 (2014)</title>
      <pubDate>Tue, 04 Feb 2014 17:41:33 GMT</pubDate>
      <guid>http://jekins.ru/blog/club_of_fans_dance_vol_2_2014/2014-02-03-491</guid>
      <link>http://jekins.ru/blog/club_of_fans_dance_vol_2_2014/2014-02-03-491</link>
    </item>
    <item>
      <link>http://twitter.com/sandlex/statuses/430756891091013632</link>
      <wfw:commentRss>http://blogs.yandex.ru/search.rss?post=http%3A%2F%2Ftwitter.com%2Fsandlex%2Fstatuses%2F430756891091013632&amp;ft=comments</wfw:commentRss>
      <yablogs:journal url="http://twitter.com/sandlex">sandlex</yablogs:journal>
      <description>RT &lt;ns0:user name="jgordijn" server="twitter.com"&gt;@jgordijn&lt;/ns0:user&gt;: Course Akka with &lt;b&gt;Scala&lt;/b&gt; Basic &amp;amp; Advanced are being held in NL! see: &lt;a href="http://t.co/QSeB4IFjc7" xhref="http://www.scalaconsulting.nl/"&gt;scalaconsulting.nl&lt;/a&gt; by &lt;ns0:user name="hseeberger" server="twitter.com"&gt;@hseeberger&lt;/ns0:user&gt; &lt;ns0:user name="typesafe" server="twitter.com"&gt;@typesafe&lt;/ns0:user&gt; &lt;ns0:tag name="scala" server="twitter.com"&gt;#&lt;b&gt;scala&lt;/b&gt;&lt;/ns0:tag&gt; &lt;ns0:tag name="akka" server="twitter.com"&gt;#akka&lt;/ns0:tag&gt;</description>
    </item>
    <item>
      <guid>http://bestsoft.at.ua/blog/ljubimye_treki_vkontakte_50_50_2014/2014-01-20-111</guid>
      <link>http://bestsoft.at.ua/blog/ljubimye_treki_vkontakte_50_50_2014/2014-01-20-111</link>
      <description>

Vlad &lt;b&gt;Scala&lt;/b&gt; &amp;amp; Rikazz - Breathe (Marbrax Remix) 028.</description>
    </item>
    <item>
      <link>http://dare-speranza.livejournal.com/172725.html?thread=3743669&amp;</link>
      <wfw:commentRss>http://blogs.yandex.ru/search.rss?post=http%3A%2F%2Fdare-speranza.livejournal.com%2F172725.html%3Fthread%3D3743669%26&amp;ft=comments</wfw:commentRss>
    </item>
    <item>
      <link>http://twitter.com/femichibi/statuses/430750048797065216</link>
    </item>
    <item>
      <author>http://twitter.com/heartysmiley</author>
      <link>http://twitter.com/heartysmiley/statuses/430749997106470912</link>
    </item>
    <item>
      <link>http://vk.com/wall215093433_679</link>
    </item>
    <item></item>
    <item>
      <pubDate>Tue, 04 Feb 2014 16:14:58 GMT</pubDate>
      <guid>http://twitter.com/figuvogaqyx/statuses/430736248336949248</guid>
      <link>http://twitter.com/figuvogaqyx/statuses/430736248336949248</link>
    </item>
    <item>
      <link>http://twitter.com/AverinaAnn/statuses/430734578391982080</link>
    </item>
  </channel>
</rss>"""
  val emptyXml = """<?xml version="1.0" encoding="utf-8"?>
<rss xmlns:yablogs="urn:yandex-blogs" xmlns:wfw="http://wellformedweb.org/CommentAPI/" version="2.0">
  <channel></channel>
</rss>"""
  val pool = new PoolUrlToLinks(3) {
	  override def getXml(url: String) = XML.loadString(fillXml)
  }
  pool.execute(Seq("")).get.flatten must have size(9)
  
  val emptyPool = new PoolUrlToLinks(3) {
    override def getXml(url: String) = XML.loadString(emptyXml)
  }
  emptyPool.execute(Seq("")).get.flatten must have size(0)  
}