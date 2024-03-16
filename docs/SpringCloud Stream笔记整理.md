1. 添加kafka stream依赖
   ```xml
   <dependencies>
      <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-stream</artifactId>
      </dependency>
      <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-stream-binder-kafka</artifactId>
      </dependency>
   </dependencies>
   ```
2. application.yml中添加配置
   ```yml
   --- #stream config
   spring:
     cloud:
       stream:
         binders:
           myKafka1:
             type: kafka
             environment:
               spring:
                 kafka:
                   bootstrap-servers: 127.0.0.1:9092
         bindings:
           helloFunc-in-0:
             destination: hello-topic
             group: hello-local-test-10
             binder: myKafka1
             consumer:
               batch-mode: true
           helloFunc-out-0:
             destination: hello-topic
             group: hello-local-test-10
             binder: myKafka1
             consumer:
               batch-mode: true
       # 注意 function 节点与stream 同级，而非子节点
       function:
         definition: helloFunc;
   ```
3. 编写消费者:
   ```java
   @Slf4j
   @Component
   @RequiredArgsConstructor
   public class HelloConsumer {
       @Bean
       public Consumer<Message<List<String>>> helloFunc() {
           return message -> {
               log.info("---------------------> ");
               List<String> list = message.getPayload();
               boolean result = this.handle(list);
               if (result) {
                   Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
                   if (acknowledgment != null) {
                       acknowledgment.acknowledge();
                   }
               } else {
                   throw new RuntimeException("消费数据出错!");
               }
           };
       }
       private boolean handle(List<String> list){
           log.info("list size : {}", list.size());
           if (!CollectionUtils.isEmpty(list)){
               log.info("group first message : {}", list.get(0));
           }
           return true ;
       }
   }
   ```