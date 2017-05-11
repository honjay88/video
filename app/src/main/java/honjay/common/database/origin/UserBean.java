package honjay.common.database.origin;

/**
 * Created by honjayChen on 2017/2/27.
 */
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class UserBean {
    @Id
    private Long id;
    private String name;
    @Generated(hash = 2024802960)
    public UserBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}