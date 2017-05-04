package com.anhoo.util;


import com.anhoo.common.BaseBean;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class ResponseUtil extends BaseBean {

    private ResponseUtil() {
        //工具类无需对象实例化
    }

    private static final int WIDTH = 120, HEIGHT = 50;

    public static void OutputContent(HttpServletResponse response,
                                     String contentType, String content) throws IOException {
        response.setContentType(contentType + ";charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(content);
        out.flush();
        out.close();
    }

    /**
     * 输出zip文件
     *
     * @param response
     * @param localZipFile
     * @throws IOException
     */
    public static void OutputZip(HttpServletResponse response,
                                 String localZipFile) throws IOException {
        File f = new File(localZipFile);
        if (!f.exists()) {
            return;
        }
        String downloadFileName = f.getName();
        f = null;
        response.setContentType("application/x-zip-compressed");
        response.setHeader("Location", downloadFileName);
        response.setHeader("Cache-Control", "max-age=-1");
        response.setHeader("Content-Disposition", "attachment; filename=" + downloadFileName);
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(localZipFile);
        response.setContentLength(inputStream.available());
        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        outputStream = null;
    }

    public static void OutputHtml(HttpServletResponse response, String content)
            throws IOException {
        OutputContent(response, "text/html", content);
    }

    public static void OutputXml(HttpServletResponse response, String content)
            throws IOException {
        OutputContent(response, "application/xml", content);
    }

    public static void OutputJson(HttpServletResponse response, String content)
            throws IOException {
        OutputContent(response, "application/json", content);
    }

    public static void OutputPlainText(HttpServletResponse response, String content)
            throws IOException {
        OutputContent(response, "text/plain", content);
    }

    public static void OutputImage(HttpServletResponse response)
            throws IOException {
        // 创建一幅图形
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // 1.设置背景色
        setBackGround(g);

        // 2.设置边框
        setBorder(g);

        // 3.画干扰线
        drawRandomLine(g);

        // 4.写随机数 返回 具体的验证值
        String validateValue = drawRandomNum((Graphics2D) g);
        //System.out.println("验证码：" + validateValue);

        // 把验证码的具体值 存到session中
        // request.getSession().setAttribute("validateValue", validateValue);

        // 5.图形写给浏览器

        // 控制浏览器缓存 System.currentTimeMillis() 获取当前时间
        // response.setDateHeader("Expires",System.currentTimeMillis() +
        // 1000*3600); //缓存1小时

        // 控制浏览器不要缓存数据
        response.setDateHeader("Expires", -1);
        response.setHeader("cache-control", "no-cache");
        response.setHeader("pragma", "no-cache");

        response.setContentType("image/jpeg");// 告诉浏览器用什么方式打开
        // 把图形以JPG的格式存到流中然后调用 ImageIO.write输到浏览器
        ImageIO.write(image, "jpg", response.getOutputStream());

    }

    private static void setBackGround(Graphics g) {
        g.setColor(Color.lightGray);// 设置背景色
        g.fillRect(0, 0, WIDTH, HEIGHT);// 让颜色去填充这个矩形

    }

    private static void setBorder(Graphics g) {
        // 设置边框的颜色 随机出现
        if (0 == new Random().nextInt(3)) {
            g.setColor(Color.cyan);// 设置边框颜色
        } else if (1 == new Random().nextInt(5)) {
            g.setColor(Color.red);// 设置边框颜色
        } else {
            g.setColor(Color.yellow);// 设置边框颜色
        }

        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);// 绘制边框
    }

    private static void drawRandomLine(Graphics g) {
        // g.setColor(Color.blue);
        // 设置颜色 随机出现
        if (0 == new Random().nextInt(3)) {
            g.setColor(Color.cyan);
        } else if (1 == new Random().nextInt(5)) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.yellow);
        }

        for (int i = 0; i < 5; i++) {
            // 随机获得初始坐标，和结束坐标
            int x1 = new Random().nextInt(WIDTH);// 横坐标要在宽度之内
            int y1 = new Random().nextInt(HEIGHT);// 纵坐标要在高度之内
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);

            g.drawLine(x1, y1, x2, y2);// 画干扰线
        }
    }

    private static String drawRandomNum(Graphics2D g) {
        String validateValue = ""; // 具体验证码的值

        g.setColor(Color.red);
        g.setFont(new Font("宋体", Font.BOLD, 28));// 设置字体

        // 汉字
        // String
        // base="\u4e00\u4e01\u4e02\u4e03\u4e04\u4e05\u4e06\u4e07\u4e08\u4e09";
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        int x = 5;
        // 中文字体范围 [\u4e00-\u9fa5]
        for (int i = 0; i < 4; i++) {
            int theta = new Random().nextInt() % 30;// 获取-30到30的数
            // String x1 = new Random().nextInt(9)+""; 随机输出数字
            String ch = base.charAt(new Random().nextInt(base.length())) + "";// 获取字

            g.rotate(theta * Math.PI / 180, x, 30);// 设置旋转角度（弧度）
            g.drawString(ch, x, 30);// 写字到方框内

            g.rotate(-theta * Math.PI / 180, x, 30);// 把位置旋转回去
            // g.drawString(x1, x, 20);
            x += 30;
            // g.drawString(x1, x, 20);
            // x+=30;
            validateValue = validateValue + ch;
        }

        return validateValue;
    }
}
