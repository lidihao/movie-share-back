package com.hao.movieshareback;


import com.hao.movieshareback.dao.PermissionMapper;
import com.hao.movieshareback.service.PermissionService;
import com.hao.movieshareback.service.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
public class BaseMailTest {

    private String receptionMailAddr = "18302026070@163.com";

    @Autowired
    private MailService mailService;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 测试 文本邮件
     * @throws Exception
     */
    @Test
    public void testSimpleMail() throws Exception {
        permissionMapper.getPermissionsByRoleId(1002);
    }

    /**
     * 测试 html 邮箱
     * @throws Exception
     */
    @Test
    public void testHtmlMail() throws Exception {
        String content=createActiveContent("李帝豪","abc");
        mailService.sendHtmlMail(receptionMailAddr,"test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String filePath="C:\\\\Users\\\\Administrator\\\\Desktop\\\\java并发学习.txt";
        mailService.sendAttachmentsMail(receptionMailAddr, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() throws MessagingException {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\\\Users\\\\Administrator\\\\Desktop\\\\testMail.png";

        mailService.sendInlineResourceMail(receptionMailAddr, "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    public String createActiveContent(String userName,String activeToken){
        String html ="<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>用户激活邮件</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div>\n" +
                "        <img src=\"http://localhost:8089/logo.png\">\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <p>\n" +
                "            亲爱的用户请点击以下链接，激活用户：\n" +
                "        </p>\n" +
                "        <a href=\"http://localhost:8080/#/user/active?userName="+userName+"&token="+activeToken+"\">激活账户</a>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        return html;
    }

}
