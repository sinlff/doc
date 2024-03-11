package temp;

import com.sinlff.server.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class AAA {
    private static volatile int total=0;

    public static void main(String[] args) throws InterruptedException {
        int threadNum=20;
        CountDownLatch countDownLatch=new CountDownLatch(threadNum);
        Thread[] threads=new Thread[threadNum];
        for(int i=0;i<threadNum;i++){
            threads[i]=new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        User user = BBB.getInstance().getUser();
                        log.info("id={}", user.getId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    total++;
                }
            });
        }

        for(int i=0;i<threadNum;i++){
            threads[i].start();
            countDownLatch.countDown();
        }

        for(int i=0;i<threadNum;i++){
            threads[i].join();
        }
        log.info("完毕,total={}",total);
    }
}