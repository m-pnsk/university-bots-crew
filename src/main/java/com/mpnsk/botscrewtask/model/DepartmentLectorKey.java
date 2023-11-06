package com.mpnsk.botscrewtask.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@Embeddable
public class DepartmentLectorKey implements Serializable {
    private Long departmentId;
    private Long lectorId;

    public DepartmentLectorKey() {}

    public DepartmentLectorKey(Long departmentId, Long lectorId) {
        this.departmentId = departmentId;
        this.lectorId = lectorId;
    }
}
