package com.epam.esm.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id"})
@ToString
public class Tag {
    @Setter(value = AccessLevel.NONE)
    private long id;

    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
