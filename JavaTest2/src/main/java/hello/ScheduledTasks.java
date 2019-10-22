package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    //日志记录器(Logger)是日志处理的核心组件。
    //比较常用的用法，就是根据类名实例化一个静态的全局日志记录器:
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //注解@Scheduled 可以作为一个触发源添加到一个方法中
    //@Scheduled(fixedDelay = 5000)这个周期是以上一个调用任务的完成时间为基准，在上一个任务完成之后，5s后再次执行：
    //Scheduled(fixedRate = 5000)以一个固定速率5s来调用一次执行，这个周期是以上一个任务开始时间为基准，从上一任务开始执行后5s再次调用：
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("the time is now {}",dateFormat.format(new Date()));
    }

}
