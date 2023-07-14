package com.xiaokaige.test;

import com.stl.util.STLJsonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;

@Data
@AllArgsConstructor
public class I18nAttach {

    private String key;

    private String value;

    private Integer index;

    private boolean i18n;

    public String singletonListJson() {
        return STLJsonUtils.obj2json(Collections.singletonList(this));
    }

    public I18nAttach(String key, String value, Integer index) {
        this.key = key;
        this.value = value;
        this.index = index;
        this.i18n = false;
    }
}
