package org.apdoer.manager.model.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
* @author apdoer
* @date 2019-04-10
*/
@Entity
@Data
@Table(name="dict")
public class DictPo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 字典名称
     */
    @Column(name = "name",nullable = false,unique = true)
    @NotBlank
    private String name;

    /**
     * 描述
     */
    @Column(name = "remark")
    private String remark;

    @OneToMany(mappedBy = "dict",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<DictDetailPo> dictDetails;

    public @interface Update {}
}