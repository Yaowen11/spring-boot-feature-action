package web.boot.action.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.boot.action.entity.Goods;
import web.boot.action.entity.JsonData;
import web.boot.action.entity.Member;
import web.boot.action.service.RabbitSender;

/**
 * @author z
 */
@RestController
@RequestMapping("/rabbit/send/")
public class RabbitSendController {
    @Autowired
    private RabbitSender directSender;
    @PostMapping("/direct")
    public JsonData direct(@RequestParam String name, @RequestParam String introduce, @RequestParam Double price) {
        Goods goods = new Goods(name, introduce, price);
        directSender.sendDirectQueue(goods);
        return new JsonData(0, "发布成功", goods);
    }
    @PostMapping("/topic")
    public JsonData topic(@RequestParam String name, @RequestParam String introduce) {
        JsonData jsonData = new JsonData();
        jsonData.setCode(0);
        jsonData.setMessage("success");
        if (System.currentTimeMillis() % 2 == 0) {
            Goods goods = new Goods();
            goods.setName(name);
            goods.setIntroduce(introduce);
            directSender.sendTopicQueue(goods);
            jsonData.setEntity(goods);
        } else {
            Member member = new Member();
            member.setName(name);
            member.setPassword(introduce);
            directSender.sendTopicQueue(member);
            jsonData.setEntity(member);
        }
        return jsonData;
    }

    @PostMapping("/fanout")
    public JsonData fanout(@RequestParam String name, @RequestParam String introduce) {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setIntroduce(introduce);
        directSender.sendFanoutQueue(goods);
        return new JsonData(0, "success", goods);
    }
}
