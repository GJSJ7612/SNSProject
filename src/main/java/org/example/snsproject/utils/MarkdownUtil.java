package org.example.snsproject.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class MarkdownUtil {

    public static String markdownToHtml(String markdown) {
        // 使用CommonMark的Parser解析Markdown文本
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown); // 直接获取Node对象

        // 使用CommonMark的HtmlRenderer将Node对象渲染为HTML字符串
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document); // 直接使用document作为参数
    }
    public static String markdownToHtmlExtensions(String markdown) {
        // h标题生成id的扩展
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        // 转换table的HTML的扩展
        List<Extension> tableExtensions = Arrays.asList(TablesExtension.create());

        Parser parser = Parser.builder()
                .extensions(tableExtensions) // 注册表格扩展
                .build();

        Node document = parser.parse(markdown); // 解析Markdown文本

        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions) // 注册标题锚点扩展
                .attributeProviderFactory(context -> new CustomAttributeProvider()) // 设置自定义属性提供者
                .build();

        return renderer.render(document); // 渲染为HTML
    }

    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(org.commonmark.node.Node node, String tagName, Map<String, String> attributes) {
            //改变a标签的target属性为_blank
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }
    }
}