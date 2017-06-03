package com.sh.model.component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author
 * @ClassName: BaseEntity
 * @Description:@MappedSuperclass可以将超类的JPA注解传递给子类,使子类能够继承超类的JPA注解
 * @date 2015年4月13日 下午9:45:59
 */
@MappedSuperclass
public class BaseEntity {

    private Long id;

    private Date createDate;

    private String createBy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


}
