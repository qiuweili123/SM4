package cn.slimsmart.rabbitmq.spring.rabbitmq.demo.rpc;

public class TestServiceImpl implements TestService {

    public String say(String msg) {
        return "hello " + msg;
    }
}
