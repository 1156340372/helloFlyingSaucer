//package com.example.flyingsaucer.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class FastdfsController {
//
//    @PostMapping("/generatePDF")
//    @ApiOperation(value="generatePDF")
//    public Map<String, Object> generatePDF(){
//        Map<String, Object> mp = new HashMap<String, Object>();
//        try {
//            String outputFile = "D:\\test\\pdf\\"+System.currentTimeMillis()+uuid+".pdf";//生成后的路径
//            Map<String, Object> dataMap = new HashMap<String, Object>();
//
//            dataMap.put("name","张三");
//            dataMap.put("img1", "1.png");
//
//            String htmlStr = HtmlGenerator.generate("html/template.html", dataMap);
//            System.out.println(htmlStr);
//
//            OutputStream out = new FileOutputStream(outputFile);
//            PdfGenerator.generatePlus(htmlStr, out);
//            mp.put("code", "200");
//            mp.put("url", outputFile);
//        } catch (Exception ex) {
//            //System.out.println(ex.getMessage());
//            mp.put("code", "500");
//        }
//        return mp;
//    }
//
//
//
//
//}
