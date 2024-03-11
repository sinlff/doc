package temp;

import com.sinlff.server.domain.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BBB {
    private volatile static BBB bbb;
    private User user;

    public static BBB getInstance(){
        if(bbb==null){
            synchronized (BBB.class){
                if (bbb==null){
                    bbb=new BBB();
                    bbb.user=new User();
                    bbb.user.setName("setName");
                    bbb.user.setEmail("setEmail");
                    bbb.user.setAge(11);
                    bbb.user.setId(1L);
                }
            }
        }
        return bbb;
    }

    public User getUser(){
        return user;
    }
}