package com.xiaokaige.plugins;

import com.google.common.base.Optional;
import com.xiaokaige.annotataion.SwaggerNotes;
import com.xiaokaige.enums.SubCodeEnum;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import static springfox.documentation.swagger.common.SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER;

/**
 * swagger注解notes值补丁
 *
 * @author lgs
 */
@Component
@Order(SWAGGER_PLUGIN_ORDER + 1000)
@ConditionalOnProperty(value = "swagger.enabled", havingValue = "true")
public class SwaggerOperationNotesReaderPlugin implements OperationBuilderPlugin {

    private final DescriptionResolver descriptions;

    /**
     * sub code 说明分隔符
     */
    private static final String SUB_CODE_SPLIT_CHAR = "：";
    /**
     * sub code 顺序分隔符
     */
    private static final String SUB_CODE_SEQUENCE_SPLIT_CHAR = "、";

    public SwaggerOperationNotesReaderPlugin(DescriptionResolver descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void apply(OperationContext context) {
        Optional<SwaggerNotes> swaggerNotesOptional = context.findAnnotation(SwaggerNotes.class);
        if (!swaggerNotesOptional.isPresent()) {
            return;
        }
        StringBuilder buffer = new StringBuilder();
        SwaggerNotes swaggerNotes = swaggerNotesOptional.get();
        String tip = swaggerNotes.tip();
        if (!StringUtils.isEmpty(tip)) {
            buffer.append(tip).append("\n");
        }
        Class<?>[] classes = swaggerNotes.subCodeType();
        String[] codeTypeArr = swaggerNotes.codeType();
        for (Class<?> clazz : classes) {
            try {
                SubCodeEnum[] sce = (SubCodeEnum[]) clazz.getEnumConstants();
                for (String code : codeTypeArr) {
                    int seq = 1;
                    for (SubCodeEnum item : sce) {
                        if (item.getSubCode().startsWith(code)) {
                            buffer.append(seq)
                                    .append(SUB_CODE_SEQUENCE_SPLIT_CHAR)
                                    .append(item.getSubCode())
                                    .append(SUB_CODE_SPLIT_CHAR)
                                    .append(item.getDesc())
                                    .append("\n");
                            seq++;
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }
        String notes = buffer.toString();
        if (!StringUtils.isEmpty(notes)) {
            context.operationBuilder().notes(descriptions.resolve(notes));
        }
    }


    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}
