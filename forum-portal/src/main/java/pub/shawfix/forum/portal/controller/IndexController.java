package pub.shawfix.forum.portal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.shawfix.forum.api.service.ArticleApiService;
import pub.shawfix.forum.portal.support.WebUtil;

import javax.annotation.Resource;

/**
 * @author shawfix
 * @create 2025/5/30 11:00
 * @desc
 **/
@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

 private static final String ALL_TYPE_NAME = "全部文章";

 @Resource
 private ArticleApiService articleApiService;

 @Resource
 private ConfigApiService configApiService;

 @Resource
 private FaqApiService faqApiService;

 @Resource
 private WebUtil webUtil;

 @Resource
 private GlobalViewConfig globalViewConfig;

 @Value("${custom-config.view.index-page.sidebar-type-names}")
 private String siderBarTypeNames;
}
