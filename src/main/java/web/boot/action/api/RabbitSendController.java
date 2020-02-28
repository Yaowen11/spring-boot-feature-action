package web.boot.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.boot.action.entity.Goods;
import web.boot.action.entity.JsonData;
import web.boot.action.service.DirectSender;

/**
 * @author z
 */
@RestController
public class RabbitSendController {
    @Autowired
    private DirectSender directSender;
    @PostMapping("/rabbit/direct")
    public JsonData store(@RequestParam String name, @RequestParam String password, @RequestParam Double price) {
        Goods goods = new Goods(name, password, price);
        directSender.sendDirectQueue(goods);
        return new JsonData(0, "发布成功", goods);
    }

}
