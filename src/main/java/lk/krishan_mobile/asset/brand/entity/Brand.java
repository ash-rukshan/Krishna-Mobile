package lk.krishan_mobile.asset.brand.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.krishan_mobile.asset.item.entity.Item;
import lk.krishan_mobile.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Brand")
public class Brand extends AuditEntity {
    @NotNull
    @Size(min = 1, message = "This name length should be more than one character")
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Item> items;
}
