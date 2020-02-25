package web.boot.action.http;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

/**
 * @author z
 */
@Controller
public class TemplateEngineController {
    @GetMapping("/template/thymeleaf")
    public String thymeleaf(ModelMap map) {
        map.put("thText", "th:text 设置文本内容 <b>加粗</b>");
        map.put("thUText", "th:utext 设置文本内容 <b>加粗</b>");
        map.put("thValue", "thValue 设置当前元素的value值");
        map.put("thEach", Arrays.asList("th:each", "遍历列表"));
        map.put("thIf", "msg is not null");
        map.put("thObject", new Object() {
            public int id = 23;
            public String name = "情种";
            public String desc = "对象";
        });
        return "thymeleaf";
    }
    @GetMapping("/template/freemarker")
    public String freemarker(ModelMap modelMap) {
        modelMap.addAttribute("msg", "Hi, zyw!");
        return "freemarker.ftl";
    }
    @GetMapping("/template/jsp")
    public String jsp(ModelMap modelMap) {
        modelMap.addAttribute("name", "zyw");
        return "servlet-jsp.jsp";
    }
}
