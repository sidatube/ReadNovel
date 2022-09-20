package com.example.readnovel.entity;

import com.example.readnovel.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "donateHistory")
public class DonateHistory extends BaseEntity {
    @Id
    @GeneratedValue(generator = "custom-name")
    @GenericGenerator(name = "custom-name",strategy = "com.example.readnovel.util.CustomId",parameters = @Parameter(name = "prefix",value = "DONATE"))
    private String id;
    private String username;
    private String code;
    private BigDecimal amount;
    @Column(updatable = false,insertable = false)
    private String accountId;
    @ManyToOne( cascade = {CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "accountId")
    @JsonBackReference
    private Account account;

}
