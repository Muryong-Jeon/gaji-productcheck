package GAJI;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Entity
@Table(name="ProductCheck_table")
public class ProductCheck {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String checkFlag;

    @PostPersist
    public void onPostPersist(){
        CheckRequested checkRequested = new CheckRequested();
        BeanUtils.copyProperties(this, checkRequested);
        checkRequested.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){
        CheckConfirmed checkConfirmed = new CheckConfirmed();
        BeanUtils.copyProperties(this, checkConfirmed);
        checkConfirmed.publishAfterCommit();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }


}
