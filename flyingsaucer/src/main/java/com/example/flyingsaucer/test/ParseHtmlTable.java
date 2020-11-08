package com.example.flyingsaucer.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.net.FileRetrieve;
import com.itextpdf.tool.xml.net.ReadingProcessor;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.*;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.StringUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class ParseHtmlTable {
    public static final String pdfDestPath = "C:\\Users\\Administrator\\Desktop\\";
    public static final String htmlPath = "F:\\developTools\\JetBrains\\workspace_idea\\flyingsaucer\\src\\main\\resources\\test.html";

    public static void main(String[] args) throws IOException, DocumentException {
        String pdfName = "test.pdf";
        ParseHtmlTable parseHtmlTable = new ParseHtmlTable();
        String htmlStr = FileUtils.readFileToString(new File(htmlPath));
        System.out.println("to html2pdf");
        parseHtmlTable.html2pdf(htmlStr,pdfName ,"F:\\font");
        System.out.println("end html2pdf");
    }


    public void html2pdf(String html, String pdfName, String fontDir) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = (ITextFontResolver) renderer.getSharedContext().getFontResolver();
            //添加字体库 begin
            File f = new File(fontDir);
            if (f.isDirectory()) {
                File[] files = f.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        String lower = name.toLowerCase();
                        return lower.endsWith(".otf") || lower.endsWith(".ttf") || lower.endsWith(".ttc");
                    }
                });
                System.out.println("files.length"+files.length);
                for (int i = 0; i < files.length; i++) {
                    // BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED   for specify fonts for a specific encoding
                    fontResolver.addFont(files[i].getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                }
            }
            //添加字体库end
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(os);
            renderer.finishPDF();
            byte[] buff = os.toByteArray();
            System.out.println("buff.size()"+buff.length);
            //保存到磁盘上
            FileUtil.byte2File(buff,pdfDestPath,pdfName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
